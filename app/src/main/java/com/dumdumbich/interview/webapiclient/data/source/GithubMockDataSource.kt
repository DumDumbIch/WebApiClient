package com.dumdumbich.interview.webapiclient.data.source

import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.GithubUsecase
import java.lang.IllegalStateException


class GithubMockDataSource : GithubUsecase {

    private val users: MutableList<User> = emptyList<User>().toMutableList()


    override fun getUser(
        login: String,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Thread {
            Thread.sleep(1_000)
            if (users.isNotEmpty()) {
                for (user in users) {
                    Thread.sleep(100)
                    if (login == user.login) {
                        onSuccess.invoke(user)
                    }
                }
            } else {
                onError(IllegalStateException("GithubMockDataSource : User by login not found"))
            }
        }.start()
    }

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Thread {
            Thread.sleep(5_000)
            onSuccess.invoke(createUsers(20))
        }.start()
    }

    override fun getUserRepository(
        userLogin: String,
        repositoryName: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getUserRepositories(
        login: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    private fun createUser(item: Int): User =
        User(
            "",
            "Login $item",
            "https//github/login$item"
        )

    private fun createUsers(items: Int): List<User> {
        for (item in 1..items) users.add(createUser(item))
        return users
    }

}