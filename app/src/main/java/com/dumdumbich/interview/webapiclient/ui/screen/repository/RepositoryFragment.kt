package com.dumdumbich.interview.webapiclient.ui.screen.repository

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.dumdumbich.interview.webapiclient.app
import com.dumdumbich.interview.webapiclient.databinding.FragmentRepositoryBinding
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.ui.base.BaseFragment

private const val ARG_USER_LOGIN = "arg_user_login"
private const val ARG_REPOSITORY_NAME = "arg_repository_name"


class RepositoryFragment : BaseFragment() {

    companion object {

        fun newInstance(userLogin: String, repositoryName: String) =
            RepositoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_LOGIN, userLogin)
                    putString(ARG_REPOSITORY_NAME, repositoryName)
                }
            }

    }

    private var _ui: FragmentRepositoryBinding? = null
    private val ui get() = _ui!!

    private val handlerThread: HandlerThread = HandlerThread("thread_pool")
    private val uiHandler by lazy { Handler(Looper.getMainLooper()) }
    private val jobHandler by lazy { Handler(handlerThread.looper) }

    private var _userLogin: String? = null
    private val userLogin get() = _userLogin ?: "JakeWharton"

    private var _repositoryName: String? = null
    private val repositoryName get() = _repositoryName ?: "Retrofit"

    private var repository: Repository? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            _userLogin = bundle.getString(ARG_USER_LOGIN)
            _repositoryName = bundle.getString(ARG_REPOSITORY_NAME)
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            app.router.showUserScreen(userLogin)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentRepositoryBinding.inflate(inflater, container, false).root

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _ui = FragmentRepositoryBinding.bind(view)
        handlerThread.start()

        showProgressBar()
        jobHandler.post {
            app.githubDataSource.getUserRepository(userLogin, repositoryName,
                onSuccess = { repository ->
                    this.repository = repository
                    uiHandler.post {
                        ui.repositoryNameTextView.text = repository.name
                        ui.repositoryForksTextView.text = "Forks : ${repository.forks}"
                        ui.repositoryPushedAtTextView.text = "Pushed at : ${repository.pushedAt}"
                        hideProgressBar()
                    }
                },
                onError = {
                    throw IllegalStateException("GitHub: user repository data receive error")
                }
            )
        }

    }

    override fun onDestroyView() {
        _ui = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        handlerThread.quit()
        super.onDestroy()
    }

    private fun showProgressBar() {
        ui.repositoryProgressBar.isVisible = true
    }

    private fun hideProgressBar() {
        ui.repositoryProgressBar.isVisible = false
    }

}