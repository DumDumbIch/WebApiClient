package com.dumdumbich.interview.webapiclient.data.storage

import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.DataStorageUsecase


class CacheDataStorage : DataStorageUsecase {

    private val _users: MutableList<User> = emptyList<User>().toMutableList()
    private val _repositories: MutableList<Repository> = emptyList<Repository>().toMutableList()


    override fun saveUser(user: User) {
        _users.add(user)
    }

    override fun isUserInStorage(login: String): Boolean =
        _users.find { it.login == login } != null


    override fun loadUser(login: String): User =
        _users.find { it.login == login }!!

    override fun saveAllUsers(users: List<User>) {
        _users.addAll(users)
    }

    override fun readAllUsers(): List<User> {
        val list: MutableList<User> = emptyList<User>().toMutableList()
        list.addAll(_users)
        return list
    }

    override fun deleteAllUsers() {
        _users.clear()
    }

    override fun saveRepository(repository: Repository) {
        _repositories.add(repository)
    }

    override fun isRepositoryInStorage(repositoryName: String, ownerLogin: String): Boolean =
        _repositories.find { it.name == repositoryName && it.ownerLogin == ownerLogin } != null


    override fun loadRepository(repositoryName: String, ownerLogin: String): Repository =
        _repositories.find { it.name == repositoryName && it.ownerLogin == ownerLogin }!!

    override fun saveAllRepositories(repositories: List<Repository>) {
        _repositories.addAll(repositories)
    }

    override fun readAllRepositories(): List<Repository> {
        val list: MutableList<Repository> = emptyList<Repository>().toMutableList()
        list.addAll(_repositories)
        return list
    }

    override fun deleteAllRepositories() {
        _repositories.clear()
    }

}