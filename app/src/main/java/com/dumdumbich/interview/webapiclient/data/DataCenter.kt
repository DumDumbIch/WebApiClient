package com.dumdumbich.interview.webapiclient.data

import android.content.Context
import android.util.Log
import android.widget.ImageView
import com.dumdumbich.interview.webapiclient.data.source.GithubMockDataSource
import com.dumdumbich.interview.webapiclient.data.source.GithubWebDataSource
import com.dumdumbich.interview.webapiclient.data.source.GlideImageLoader
import com.dumdumbich.interview.webapiclient.data.storage.CacheDataStorage
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataCenterUsecase
import com.dumdumbich.interview.webapiclient.domain.usecase.DataSourceUsecase
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase
import com.dumdumbich.interview.webapiclient.domain.usecase.ImageLoaderUsecase


class DataCenter(context: Context) : DataCenterUsecase {

    private val TAG = "@@@  ${this::class.java.simpleName} : ${this.hashCode()}"

    private val githubDataSource: DataSourceUsecase by lazy { GithubWebDataSource() }
//    private val githubDataSource: DataSourceUsecase by lazy { GithubMockDataSource() }

    private val imageLoader: ImageLoaderUsecase<ImageView> by lazy { GlideImageLoader() }

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
        dataStorage.getUser(
            login,
            onSuccess = { user ->
                Log.d(TAG, "getUser() called - dataStorage.getUser - onSuccess")
                onSuccess(user)
            },
            onError = {
                githubDataSource.getUser(
                    login,
                    onSuccess = { user ->
                        Log.d(TAG, "getUser() called - githubDataSource.getUser - onSuccess")
                        dataStorage.putUser(
                            user,
                            onSuccess = {
                                Log.d(TAG, "getUser() called - dataStorage.putUser - onSuccess")
                                onSuccess(user)
                            },
                            onError = {
                                Log.d(TAG, "getUser() called - dataStorage.putUser - onError")
                                onError(it)
                            }
                        )
                    },
                    onError = {
                        Log.d(TAG, "getUser() called - dataStorage.getUser - onError")
                        onError(it)
                    }
                )
            }
        )
    }

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        dataStorage.getUsers(
            onSuccess = {
                Log.d(TAG, "getUsers() called - dataStorage.getUsers - onSuccess")
                onSuccess(it)
            },
            onError = {
                Log.d(TAG, "getUsers() called - dataStorage.getUsers - onError")
                githubDataSource.getUsers(
                    onSuccess = { users ->
                        Log.d(TAG, "getUsers() called - githubDataSource.getUsers - onSuccess")
                        dataStorage.putUsers(
                            users,
                            onSuccess = {
                                Log.d(
                                    TAG,
                                    "getUsers() called - dataStorage.putUsers - onSuccess - put $it users"
                                )
                                onSuccess(users)
                            },
                            onError = {
                                Log.d(TAG, "getUsers() called - dataStorage.putUsers - onError")
                                onError(it)
                            }
                        )
                    },
                    onError = {
                        Log.d(TAG, "getUsers() called - githubDataSource.getUsers - onError")
                        onError(it)
                    }
                )
            }
        )
    }

    override fun getRepository(
        repositoryName: String,
        userLogin: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        dataStorage.getRepository(
            repositoryName,
            userLogin,
            onSuccess = { repository ->
                onSuccess(repository)
            },
            onError = {
                githubDataSource.getRepository(
                    repositoryName,
                    userLogin,
                    onSuccess = { repository ->
                        dataStorage.putRepository(
                            repository,
                            onSuccess = {
                                onSuccess(repository)
                            },
                            onError = {
                                onError(it)
                            }
                        )
                    },
                    onError = {
                        onError(it)
                    }
                )
            }
        )
    }

    override fun getRepositories(
        userLogin: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        dataStorage.getRepositories(
            userLogin,
            onSuccess = {
                Log.d(TAG, "getRepositories() called - dataStorage.getRepositories - onSuccess")
                onSuccess(it)
            },
            onError = {
                Log.d(TAG, "getRepositories() called - dataStorage.getRepositories - onError")
                githubDataSource.getRepositories(
                    userLogin,
                    onSuccess = { repositories ->
                        Log.d(
                            TAG,
                            "getRepositories() - githubDataSource.getRepositories - onSuccess"
                        )
                        dataStorage.putRepositories(
                            repositories,
                            onSuccess = {
                                Log.d(
                                    TAG,
                                    "getRepositories() - dataStorage.putRepositories - onSuccess - put $it repositories"
                                )
                                onSuccess(repositories)
                            },
                            onError = {
                                Log.d(
                                    TAG,
                                    "getRepositories() - dataStorage.putRepositories - onError"
                                )
                                onError(it)
                            }
                        )
                    },
                    onError = {
                        Log.d(TAG, "getRepositories() - githubDataSource.getRepositories - onError")
                        onError(it)
                    }
                )
            }
        )
    }

    override fun putUser(
        user: User,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        dataStorage.putUser(
            user,
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError(it)
            }
        )
    }

    override fun putUsers(
        users: List<User>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        dataStorage.putUsers(
            users,
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError(it)
            }
        )
    }

    override fun putRepository(
        repository: Repository,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        dataStorage.putRepository(
            repository,
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError(it)
            }
        )
    }

    override fun putRepositories(
        repositories: List<Repository>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        dataStorage.putRepositories(
            repositories,
            onSuccess = {
                onSuccess(it)
            },
            onError = {
                onError(it)
            }
        )
    }

    override fun loadImage(imageUrl: String, container: ImageView) {
        imageLoader.loadImage(imageUrl, container)
    }

}