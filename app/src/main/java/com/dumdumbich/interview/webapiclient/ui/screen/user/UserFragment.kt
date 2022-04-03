package com.dumdumbich.interview.webapiclient.ui.screen.user

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.core.view.isVisible
import com.dumdumbich.interview.webapiclient.app
import com.dumdumbich.interview.webapiclient.databinding.FragmentUserBinding
import com.dumdumbich.interview.webapiclient.domain.entity.Repository
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.ui.base.BaseFragment


private const val ARG_USER_LOGIN = "arg_user_login"


class UserFragment : BaseFragment() {

    companion object {

        fun newInstance(userLogin: String) =
            UserFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_USER_LOGIN, userLogin)
                }
            }

    }


    private var _ui: FragmentUserBinding? = null
    private val ui get() = _ui!!

    private val handlerThread: HandlerThread = HandlerThread("thread_pool")
    private val uiHandler by lazy { Handler(Looper.getMainLooper()) }
    private val jobHandler by lazy { Handler(handlerThread.looper) }

    private var _userLogin: String? = null
    private val userLogin get() = _userLogin ?: "JakeWharton"

    private var user: User? = null

    private lateinit var itemsListAdapter: ItemsListAdapter
    private val repositories: MutableList<Repository> = emptyList<Repository>().toMutableList()

    private val listener = object : ItemActionListener {

        override fun onItemShortClickListener(repository: Repository) {
            Toast.makeText(
                requireContext(),
                "Short click on item: ${repository.name}",
                Toast.LENGTH_SHORT
            ).show()
            app.router.showRepositoryScreen(userLogin, repository.name)
        }

        override fun onItemLongClickListener(repository: Repository, anchor: View): Boolean {
            Toast.makeText(
                requireContext(),
                "Long click on item: ${repository.name}",
                Toast.LENGTH_SHORT
            ).show()
            return true
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            _userLogin = bundle.getString(ARG_USER_LOGIN)
        }

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            app.router.showUsersListScreen()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUserBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _ui = FragmentUserBinding.bind(view)
        handlerThread.start()

        itemsListAdapter = ItemsListAdapter(repositories, listener)
        ui.briefInfoRepositoryItemRecyclerView.adapter = itemsListAdapter

        showUserProgressBar()
        jobHandler.post {
            app.dataCenter.getUser(
                userLogin,
                onSuccess = { user ->
                    this.user = user
                    uiHandler.post {
                        ui.userLoginTextView.text = user.login
                        ui.userRepositoryUrlTextView.text = user.reposUrl
                        app.dataCenter.loadImage(user.avatarUrl, ui.userAvatarImageView)
                        hideUserProgressBar()
                    }
                },
                onError = {
                    throw IllegalStateException("DataCenter: user data receive error")
                }
            )
        }

        showRepositoriesProgressBar()
        jobHandler.post {
            app.dataCenter.getRepositories(
                userLogin,
                onSuccess = { repositories ->
                    this.repositories.clear()
                    this.repositories.addAll(repositories)
                    uiHandler.post {
                        itemsListAdapter.setData(repositories)
                        hideRepositoriesProgressBar()
                    }
                },
                onError = {
                    throw IllegalStateException("DataCenter: user repositories data receive error")
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

    private fun showUserProgressBar() {
        ui.userProgressBar.isVisible = true
    }

    private fun hideUserProgressBar() {
        ui.userProgressBar.isVisible = false
    }

    private fun showRepositoriesProgressBar() {
        ui.userRepositoriesProgressBar.isVisible = true
    }

    private fun hideRepositoriesProgressBar() {
        ui.userRepositoriesProgressBar.isVisible = false
    }

}