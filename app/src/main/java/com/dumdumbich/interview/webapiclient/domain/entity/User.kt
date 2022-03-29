package com.dumdumbich.interview.webapiclient.domain.entity


data class User(
    val avatarUrl: String,
    val login: String,
    val reposUrl: String,
    val publicRepositoriesNumber: Int = 0
)
