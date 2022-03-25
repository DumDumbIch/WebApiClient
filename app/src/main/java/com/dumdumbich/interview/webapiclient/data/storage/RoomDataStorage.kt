package com.dumdumbich.interview.webapiclient.data.storage

import android.content.Context
import androidx.room.Room
import com.dumdumbich.interview.webapiclient.data.db.room.GithubDb
import com.dumdumbich.interview.webapiclient.data.db.room.repository.RepositoryDao
import com.dumdumbich.interview.webapiclient.data.db.room.user.UserDao
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase


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
        TODO("Not yet implemented")
    }

    override fun saveAllUsers(users: List<User>) {
        TODO("Not yet implemented")
    }

    override fun readAllUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override fun deleteAllUsers() {
        TODO("Not yet implemented")
    }

    override fun saveRepository(repository: Repository) {
        TODO("Not yet implemented")
    }

    override fun saveAllRepositories(repository: List<Repository>) {
        TODO("Not yet implemented")
    }

    override fun readAllRepositories(): List<Repository> {
        TODO("Not yet implemented")
    }

    override fun deleteAllRepositories() {
        TODO("Not yet implemented")
    }

}