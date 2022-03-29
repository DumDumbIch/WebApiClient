package com.dumdumbich.interview.webapiclient.domain.entity


data class Repository(
    val name: String,
    val ownerLogin: String,
    val pushedAt: String,
    val forksNumber: Int,
    val language: String?
)
