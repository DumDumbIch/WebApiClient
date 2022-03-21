package com.dumdumbich.interview.webapiclient.data.source

import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.GithubUsecase


class GithubMockDataSource : GithubUsecase {

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        Thread {
            Thread.sleep(5_000)
            onSuccess.invoke(createUsers(20))
        }.start()
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

    private fun createUsers(items: Int) : List<User> {
        val users = ArrayList<User>()
        for (item in 1..items) users.add(createUser(item))
        return users
    }

}