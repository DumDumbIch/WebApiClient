package com.dumdumbich.interview.webapiclient

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.dumdumbich.interview.webapiclient.data.source.GithubMockDataSource
import com.dumdumbich.interview.webapiclient.domain.usecase.GithubUsecase


class App : Application() {

    val githubDataSource: GithubUsecase by lazy { GithubMockDataSource() }

}


val Context.app
    get() = applicationContext as App

val Fragment.app
    get() = requireActivity().app

val AndroidViewModel.app
    get() = getApplication<App>()