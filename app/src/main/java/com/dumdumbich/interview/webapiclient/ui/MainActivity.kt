package com.dumdumbich.interview.webapiclient.ui

import android.os.Bundle
import com.dumdumbich.interview.webapiclient.app
import com.dumdumbich.interview.webapiclient.databinding.ActivityMainBinding
import com.dumdumbich.interview.webapiclient.ui.base.BaseActivity
import com.dumdumbich.interview.webapiclient.ui.screen.repository.RepositoryFragment
import com.dumdumbich.interview.webapiclient.ui.screen.user.UserFragment
import com.dumdumbich.interview.webapiclient.ui.screen.users.UsersListFragment


class MainActivity : BaseActivity(), Router {

    private lateinit var ui: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)
        app.setRouter(this)
        if (savedInstanceState == null) showUsersListScreen()
    }

    override fun showUsersListScreen() {
        ui.mainFragmentContainer.let { container ->
            supportFragmentManager.beginTransaction()
                .replace(container.id, UsersListFragment())
                .commit()
        }
    }

    override fun showUserScreen(userLogin: String) {
        ui.mainFragmentContainer.let { container ->
            supportFragmentManager.beginTransaction()
                .replace(container.id, UserFragment.newInstance(userLogin))
                .commit()
        }
    }

    override fun showRepositoryScreen(userLogin: String, repositoryName: String) {
        ui.mainFragmentContainer.let { container ->
            supportFragmentManager.beginTransaction()
                .replace(container.id, RepositoryFragment.newInstance(userLogin, repositoryName))
                .commit()
        }
    }

}