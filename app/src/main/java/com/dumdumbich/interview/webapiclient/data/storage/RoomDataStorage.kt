package com.dumdumbich.interview.webapiclient.data.storage

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.dumdumbich.interview.webapiclient.data.db.room.GithubDb
import com.dumdumbich.interview.webapiclient.data.db.room.repository.RepositoryDao
import com.dumdumbich.interview.webapiclient.data.db.room.repository.RepositoryEntity
import com.dumdumbich.interview.webapiclient.data.db.room.user.UserDao
import com.dumdumbich.interview.webapiclient.data.db.room.user.UserEntity
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase


class RoomDataStorage(context: Context) : DataStorageUsecase {

    private val TAG = "@@@  ${this::class.java.simpleName} : ${this.hashCode()}"

    private val userDao: UserDao
    private val repositoryDao: RepositoryDao


    init {
        val db = Room.databaseBuilder(
            context,
            GithubDb::class.java,
            "github.db"
        ).build()
        userDao = db.userDao()
        repositoryDao = db.repositoryDao()
    }


    override fun getUser(
        login: String,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d(TAG, "getUser() called with: login = $login")
        Thread {
            try {
                val entity = userDao.get(login)
                onSuccess(userFromEntity(entity))
            } catch (t: Throwable) {
                onError(t)
            }
        }.start()
    }

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d(TAG, "getUsers() called")
        Thread {
            try {
                val entities = userDao.getAll()
                if (entities.isEmpty()) {
                    onError(IllegalArgumentException("Room data storage: user table is empty"))
                }
                val users = emptyList<User>().toMutableList()
                for (entity in entities) {
                    users.add(userFromEntity(entity))
                }
                onSuccess(users)
            } catch (t: Throwable) {
                onError(t)
            }
        }.start()
    }

    override fun getRepository(
        repositoryName: String,
        userLogin: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d(
            TAG,
            "getRepository() called with: repositoryName = $repositoryName, userLogin = $userLogin"
        )
        Thread {
            try {
                val entity = repositoryDao.get(repositoryName, userLogin)
                onSuccess(repositoryFromEntity(entity))
            } catch (t: Throwable) {
                onError(t)
            }
        }.start()
    }

    override fun getRepositories(
        userLogin: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d(
            TAG,
            "getRepositories() called with: userLogin = $userLogin"
        )
        Thread {
            try {
                val entities = repositoryDao.get(userLogin)
                if (entities.isEmpty()) {
                    onError(IllegalArgumentException("Room data storage: repository table is empty"))
                }
                val repositories = emptyList<Repository>().toMutableList()
                for (entity in entities) {
                    repositories.add(repositoryFromEntity(entity))
                }
                onSuccess(repositories)
            } catch (t: Throwable) {
                onError(t)
            }
        }.start()
    }

    override fun putUser(
        user: User,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d(
            TAG,
            "putUser() called with: user = $user"
        )
        Thread {
            try {
                userDao.insert(userToEntity(user))
                onSuccess(1)
            } catch (t: Throwable) {
                onError(t)
            }
        }.start()
    }

    override fun putUsers(
        users: List<User>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d(
            TAG,
            "putUsers() called with: users = $users"
        )
        Thread {
            try {
                for (user in users) {
                    Log.d(
                        TAG,
                        "putUsers() for (user in users): user = $user"
                    )
                    userDao.insert(userToEntity(user))
                }
                onSuccess(users.size)
            } catch (t: Throwable) {
                onError(t)
            }
        }.start()
    }

    override fun putRepository(
        repository: Repository,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d(
            TAG,
            "putRepository() called with: repository = $repository"
        )
        Thread {
            try {
                repositoryDao.insert(repositoryToEntity(repository))
                onSuccess(1)
            } catch (t: Throwable) {
                onError(t)
            }
        }.start()
    }

    override fun putRepositories(
        repositories: List<Repository>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Log.d(
            TAG,
            "putRepositories() called with: repositories = $repositories"
        )
        Thread {
            try {
                for (repository in repositories) {
                    repositoryDao.insert(repositoryToEntity(repository))
                }
                onSuccess(repositories.size)
            } catch (t: Throwable) {
                onError(t)
            }
        }.start()
    }

    private fun userToEntity(user: User): UserEntity =
        UserEntity(
            user.avatarUrl,
            user.login,
            user.reposUrl,
            user.publicRepositoriesNumber
        )

    private fun userFromEntity(user: UserEntity): User =
        User(
            user.avatarUrl,
            user.login,
            user.reposUrl,
            user.publicRepos
        )

    private fun repositoryToEntity(repository: Repository): RepositoryEntity =
        RepositoryEntity(
            repository.name,
            repository.ownerLogin,
            repository.pushedAt,
            repository.forksNumber,
            repository.language
        )

    private fun repositoryFromEntity(repository: RepositoryEntity): Repository =
        Repository(
            repository.name,
            repository.ownerLogin,
            repository.pushedAt,
            repository.forks,
            repository.language,
        )

}