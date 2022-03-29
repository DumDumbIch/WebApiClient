package com.dumdumbich.interview.webapiclient.domain.usecase

import com.dumdumbich.interview.webapiclient.domain.entity.User


interface PutUserUsecase {

    fun putUser(
        user: User,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    )

    fun putUsers(
        users: List<User>,
        onSuccess: (Int) -> Unit,
        onError: (Throwable) -> Unit
    )

}