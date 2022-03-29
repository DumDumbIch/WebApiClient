package com.dumdumbich.interview.webapiclient.data.storage

import android.content.Context
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase


class RoomDataStorage(context: Context) : DataStorageUsecase {

    override fun getUser(
        login: String,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getRepository(
        repositoryName: String,
        userLogin: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getRepositories(
        userLogin: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun putUser(
        user: User,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun putUsers(
        users: List<User>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun putRepository(
        repository: Repository,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun putRepositories(
        repositories: List<Repository>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}