package com.dumdumbich.interview.webapiclient.data.storage

import android.content.Context
import androidx.room.Room
import com.dumdumbich.interview.webapiclient.data.db.room.GithubDb
import com.dumdumbich.interview.webapiclient.data.db.room.repository.RepositoryDao
import com.dumdumbich.interview.webapiclient.data.db.room.repository.RepositoryEntity
import com.dumdumbich.interview.webapiclient.data.db.room.user.UserDao
import com.dumdumbich.interview.webapiclient.data.db.room.user.UserEntity
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase
import java.util.*


class RoomDataStorage(context: Context) : DataStorageUsecase {

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


    override fun saveUser(user: User) {
        userDao.insert(userToEntity(user, UUID.randomUUID().toString()))
    }

    override fun isUserInStorage(login: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun loadUser(login: String): User =
        userFromEntity(userDao.get(login))

    override fun saveAllUsers(users: List<User>) {
        for (user in users) {
            saveUser(user)
        }
    }

    override fun readAllUsers(): List<User> {
        val entities = userDao.getAll()
        val users = emptyList<User>().toMutableList()
        for (entity in entities) {
            users.add(userFromEntity(entity))
        }
        return users
    }

    override fun deleteAllUsers() {
        userDao.deleteAll()
    }

    override fun saveRepository(repository: Repository) {
        repositoryDao.insert(repositoryToEntity(repository, UUID.randomUUID().toString()))
    }

    override fun isRepositoryInStorage(repositoryName: String, ownerLogin: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun loadRepository(repositoryName: String, ownerLogin: String): Repository =
        repositoryFromEntity(repositoryDao.get(repositoryName, ownerLogin))

    override fun saveAllRepositories(repositories: List<Repository>) {
        for (repository in repositories) {
            saveRepository(repository)
        }
    }

    override fun readAllRepositories(): List<Repository> {
        val entities = repositoryDao.getAll()
        val repositories = emptyList<Repository>().toMutableList()
        for (entity in entities) {
            repositories.add(repositoryFromEntity(entity))
        }
        return repositories
    }

    override fun deleteAllRepositories() {
        repositoryDao.deleteAll()
    }

    private fun userToEntity(user: User, tableId: String): UserEntity =
        UserEntity(
            user.avatarUrl,
            user.login,
            user.reposUrl,
            user.id,
            tableId
        )

    private fun userFromEntity(user: UserEntity): User =
        User(
            user.avatarUrl,
            user.login,
            user.reposUrl,
            user.id
        )

    private fun repositoryToEntity(repository: Repository, tableId: String): RepositoryEntity =
        RepositoryEntity(
            repository.name,
            repository.ownerLogin,
            repository.pushedAt,
            repository.forks,
            repository.id,
            tableId
        )

    private fun repositoryFromEntity(repository: RepositoryEntity): Repository =
        Repository(
            repository.name,
            repository.ownerLogin,
            repository.pushedAt,
            repository.forks,
            repository.id,
        )

}