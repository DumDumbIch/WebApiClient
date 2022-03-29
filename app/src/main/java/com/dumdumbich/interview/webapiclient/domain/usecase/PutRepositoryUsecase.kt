package com.dumdumbich.interview.webapiclient.domain.usecase

import com.dumdumbich.interview.webapiclient.domain.entity.Repository


interface PutRepositoryUsecase {

    fun putRepository(
        repository: Repository,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun putRepositories(
        repositories: List<Repository>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    )

}