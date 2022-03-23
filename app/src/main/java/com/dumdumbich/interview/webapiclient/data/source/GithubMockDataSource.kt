package com.dumdumbich.interview.webapiclient.data.source


import android.annotation.SuppressLint
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.domain.usecase.GithubUsecase
import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*


class GithubMockDataSource : GithubUsecase {

    private val users: MutableList<User> = emptyList<User>().toMutableList()
    private val repositories: MutableList<Repository> = emptyList<Repository>().toMutableList()


    override fun getUser(
        login: String,
        onSuccess: (User) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        if (users.isNotEmpty()) {
            for (user in users) {
                if (login == user.login) {
                    onSuccess(user)
                }
            }
        } else {
            onError(IllegalStateException("GithubMockDataSource : User by login not found"))
        }
    }

    override fun getUsers(
        onSuccess: (List<User>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onSuccess(createUsers(20))
    }

    override fun getUserRepository(
        userLogin: String,
        repositoryName: String,
        onSuccess: (Repository) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onSuccess(createRepository())
    }

    override fun getUserRepositories(
        login: String,
        onSuccess: (List<Repository>) -> Unit,
        onError: (Throwable) -> Unit
    ) {
        onSuccess(createRepositories(20))
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

    private fun createRepository(): Repository =
        Repository(
            "Name ${UUID.randomUUID().toString()}",
            getFictionDateTime(),
            Random().nextInt(10)
        )

    private fun createRepositories(items: Int): List<Repository> {
        for (item in 1..items) repositories.add(createRepository())
        return repositories
    }

    @SuppressLint("SimpleDateFormat")
    private fun getFictionDateTime(): String {
        val calendar = GregorianCalendar()
        calendar.set(Calendar.ERA, GregorianCalendar.AD)
        val dataFormat = SimpleDateFormat("dd MMM yyy GG")
        return dataFormat.format(calendar.time)
    }

}