package com.dumdumbich.interview.webapiclient.domain.usecase

import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User


interface GithubUsecase {

    fun getUsers(onSuccess: (List<User>) -> Unit, onError: (Throwable) -> Unit)

    fun getUserRepositories(login: String, onSuccess: (List<Repository>) -> Unit, onError: (Throwable) -> Unit)

}