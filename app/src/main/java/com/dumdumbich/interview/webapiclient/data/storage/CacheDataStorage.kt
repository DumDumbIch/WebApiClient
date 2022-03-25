package com.dumdumbich.interview.webapiclient.data.storage

import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase


class CacheDataStorage : DataStorageUsecase {

    private val users: MutableList<User> = emptyList<User>().toMutableList()


    override fun saveUser(user: User) {
        this.users.add(user)
    }

    override fun saveAllUsers(users: List<User>) {
        this.users.addAll(users)
    }

    override fun readAllUsers(): List<User> {
        val list: MutableList<User> = emptyList<User>().toMutableList()
        list.addAll(this.users)
        return list
    }

    override fun deleteAllUsers() {
        users.clear()
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