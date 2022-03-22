package com.dumdumbich.interview.webapiclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.dumdumbich.interview.webapiclient.data.source.GithubWebDataSource
import com.dumdumbich.interview.webapiclient.domain.usecase.GithubUsecase
import com.dumdumbich.interview.webapiclient.ui.Router


class App : Application() {

    val githubDataSource: GithubUsecase by lazy { GithubWebDataSource() }

    private var _router: Router? = null
    val router get() = _router!!


    fun setRouter(router: Router?) {
        _router = router
    }

}


val Context.app
    get() = applicationContext as App

val Fragment.app
    get() = requireActivity().app

val AndroidViewModel.app
    get() = getApplication<App>()