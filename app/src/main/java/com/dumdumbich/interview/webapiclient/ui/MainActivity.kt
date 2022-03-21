package com.dumdumbich.interview.webapiclient.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dumdumbich.interview.webapiclient.databinding.ActivityMainBinding
import com.dumdumbich.interview.webapiclient.ui.screen.users.UsersListFragment


class MainActivity : AppCompatActivity() {

    private lateinit var ui: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ui = ActivityMainBinding.inflate(layoutInflater)
        setContentView(ui.root)

        ui.mainFragmentContainer.let { container ->
            supportFragmentManager.beginTransaction()
                .replace(container.id, UsersListFragment())
                .commit()
        }

    }

}