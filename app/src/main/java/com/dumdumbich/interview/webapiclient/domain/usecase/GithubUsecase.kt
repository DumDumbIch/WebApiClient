package com.dumdumbich.interview.webapiclient.domain.usecase

import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User


interface GithubUsecase {

    fun getUser(
        login: String, onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun getUserRepository(
        userLogin: String,
        repositoryName: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun getUserRepositories(
        login: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    )

}