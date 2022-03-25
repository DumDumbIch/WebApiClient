package com.dumdumbich.interview.webapiclient.domain.usecase

import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User


interface DataStorageUsecase {
//    CRUD Interface (Create - Read - Update - Delete)

    //    User
    fun saveUser(user: User)
    fun saveAllUsers(users: List<User>)
    fun readAllUsers(): List<User>
    fun deleteAllUsers()

    //    Repository
    fun saveRepository(repository: Repository)
    fun saveAllRepositories(repository: List<Repository>)
    fun readAllRepositories(): List<Repository>
    fun deleteAllRepositories()

}