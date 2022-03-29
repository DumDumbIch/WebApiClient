package com.dumdumbich.interview.webapiclient.domain.usecase

import com.dumdumbich.interview.webapiclient.domain.entity.Repository


interface GetRepositoryUsecase {

    fun getRepository(
        repositoryName: String,
        userLogin: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun getRepositories(
        userLogin: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    )

}