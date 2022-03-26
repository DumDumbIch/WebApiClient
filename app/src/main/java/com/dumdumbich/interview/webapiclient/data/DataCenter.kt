package com.dumdumbich.interview.webapiclient.data

import android.content.Context
import com.dumdumbich.interview.webapiclient.data.source.GithubMockDataSource
import com.dumdumbich.interview.webapiclient.data.source.GithubWebDataSource
import com.dumdumbich.interview.webapiclient.data.storage.CacheDataStorage
import com.dumdumbich.interview.webapiclient.data.storage.RoomDataStorage
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataCenterUseCase
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase
import com.dumdumbich.interview.webapiclient.domain.usecase.GithubUsecase


class DataCenter(context: Context) : DataCenterUseCase {

    private val githubDataSource: GithubUsecase by lazy { GithubWebDataSource() }
//    private val githubDataSource: GithubUsecase by lazy { GithubMockDataSource() }

    private val dataStorage: DataStorageUsecase

    init {
//        dataStorage = RoomDataStorage(context)
        dataStorage = CacheDataStorage()
    }


    override fun getUser(
        login: String,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (dataStorage.isUserInStorage(login)) {
            onSuccess(dataStorage.loadUser(login))
        } else {
            githubDataSource.getUser(
                login,
                onSuccess = {
                    onSuccess(it)
                },
                onError = {
                    onError(it)
                }
            )
        }
    }

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        val users = dataStorage.readAllUsers()
        if (users.isNotEmpty()) {
            onSuccess(users)
        } else {
            githubDataSource.getUsers(
                onSuccess = {
                    dataStorage.saveAllUsers(it)
                    onSuccess(it)
                },
                onError = {
                    onError(it)
                }
            )
        }
    }

    override fun getUserRepository(
        userLogin: String,
        repositoryName: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    ) {

        githubDataSource.getUserRepository(
            userLogin,
            repositoryName,
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError(it)
            }
        )
    }

    override fun getUserRepositories(
        login: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        githubDataSource.getUserRepositories(
            login,
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError(it)
            }
        )
    }

}