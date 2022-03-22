package com.dumdumbich.interview.webapiclient.ui


interface Router {
    fun showUsersListScreen()
    fun showUserScreen(userLogin: String)
    fun showRepositoryScreen(userLogin: String, repositoryName: String)
}