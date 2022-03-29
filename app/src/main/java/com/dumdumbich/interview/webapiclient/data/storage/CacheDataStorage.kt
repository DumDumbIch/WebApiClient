package com.dumdumbich.interview.webapiclient.data.storage

import android.util.Log
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase


class CacheDataStorage : DataStorageUsecase {

    private val TAG = "@@@  ${this::class.java.simpleName} : ${this.hashCode()}"

    private val _users: MutableList<User> = emptyList<User>().toMutableList()
    private val _repositories: MutableList<Repository> = emptyList<Repository>().toMutableList()


    override fun getUser(
        login: String,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (_users.isNotEmpty()) {
            val user: User? = _users.find { it.login == login }
            if (user != null) {
                onSuccess(user)
            } else {
                onError(IllegalArgumentException("User with login $login is missing in Users Mock data storage"))
            }
        } else {
            onError(IllegalArgumentException("Users Mock data storage is empty"))
        }
    }

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (_users.isNotEmpty()) {
            val users: MutableList<User> = emptyList<User>().toMutableList()
            users.addAll(_users)
            onSuccess(users)
        } else {
            onError(IllegalArgumentException("Users Mock data storage is empty"))
        }
    }

    override fun getRepository(
        repositoryName: String,
        userLogin: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        var user: User? = null
        getUser(
            userLogin,
            onSuccess = {
                user = it
            },
            onError = {
                onError(it)
            }
        )
        if (_repositories.isNotEmpty()) {
            val repository: Repository? =
                _repositories.find { it.name == repositoryName && it.ownerLogin == user?.login }
            if (repository != null) {
                onSuccess(repository)
            } else {
                onError(IllegalArgumentException("Repository $repositoryName of User with login $userLogin is missing in Repository Mock data storage"))
            }
        } else {
            onError(IllegalArgumentException("Repositories Mock data storage is empty"))
        }
    }

    override fun getRepositories(
        userLogin: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        var user: User? = null
        getUser(
            userLogin,
            onSuccess = {
                user = it
            },
            onError = {
                onError(it)
            }
        )
        if (_repositories.isNotEmpty()) {
            val repositories: MutableList<Repository> = emptyList<Repository>().toMutableList()
            for (repository in _repositories) {
                if (repository.ownerLogin == user?.login) {
                    repositories.add(repository)
                }
            }
            if (repositories.isNotEmpty()) {
                onSuccess(repositories)
            } else {
                onError(IllegalArgumentException("Repositories of User with login $userLogin are missing in Repository Mock data storage"))
            }
        } else {
            onError(IllegalArgumentException("Repositories Mock data storage is empty"))
        }
    }

    override fun putUser(
        user: User,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (_users.contains(user)) {
            onSuccess(0)
        } else {
            _users.add(user)
            onSuccess(1)
        }
    }

    override fun putUsers(
        users: List<User>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        var count = 0
        for (user in users) {
            putUser(
                user,
                onSuccess = {
                    Log.d(TAG, "putUsers() called - ${user.toString()}")
                    count += it
                },
                onError = {
                    onError(it)
                }
            )
        }
        onSuccess(count)
    }

    override fun putRepository(
        repository: Repository,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (_repositories.contains(repository)) {
            Log.d(
                TAG,
                "putRepository() called - repository already exist"
            )
            onSuccess(0)
        } else {
            val user: User? = _users.find {
                Log.d(TAG, "putRepository() called - $it")
                it.login == repository.ownerLogin
            }
            Log.d(TAG, "putRepository() called - found user: $user")
            Log.d(TAG, "putRepository() called - repository: $repository")
            if (user != null) {
                Log.d(
                    TAG,
                    "putRepository() called - repository added"
                )
                _repositories.add(repository)
                onSuccess(1)
            } else {
                Log.d(
                    TAG,
                    "putRepository() called - addition error"
                )
                onError(IllegalArgumentException("User with id ${repository.ownerLogin} is missing in Users Mock data storage"))
            }
        }
    }

    override fun putRepositories(
        repositories: List<Repository>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        var count = 0
        for (repository in repositories) {
            putRepository(
                repository,
                onSuccess = {
                    count += it
                },
                onError = {
                    onError(it)
                }
            )
        }
        onSuccess(count)
    }

}