package com.dumdumbich.interview.webapiclient.ui.screen.users

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import com.dumdumbich.interview.webapiclient.app
import com.dumdumbich.interview.webapiclient.databinding.FragmentUsersListBinding
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.ui.base.BaseFragment


class UsersListFragment : BaseFragment() {

    private var _ui: FragmentUsersListBinding? = null
    private val ui get() = _ui!!

    private val handlerThread: HandlerThread = HandlerThread("thread_pool")
    private val uiHandler by lazy { Handler(Looper.getMainLooper()) }
    private val jobHandler by lazy { Handler(handlerThread.looper) }

    private lateinit var itemsListAdapter: ItemsListAdapter
    private val users: MutableList<User> = emptyList<User>().toMutableList()

    private val listener = object : ItemActionListener {

        override fun onItemShortClickListener(user: User) {
            Toast.makeText(
                requireContext(),
                "Short click on item: ${user.login}",
                Toast.LENGTH_SHORT
            ).show()
            app.router.showUserScreen(user.login)
        }

        override fun onItemLongClickListener(user: User, anchor: View): Boolean {
            Toast.makeText(
                requireContext(),
                "Long click on item: ${user.login}",
                Toast.LENGTH_SHORT
            ).show()
            return true
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUsersListBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _ui = FragmentUsersListBinding.bind(view)
        handlerThread.start()

        itemsListAdapter = ItemsListAdapter(users, listener)
        ui.briefInfoItemRecyclerView.adapter = itemsListAdapter

        showProgressBar()
        app.githubDataSource.getUsers(
            onSuccess = { users ->
                this.users.clear()
                this.users.addAll(users)
                uiHandler.post {
                    itemsListAdapter.setData(users)
                    hideProgressBar()
                }
            },
            onError = {
                throw IllegalStateException("GitHub: users data receive error")
            }
        )

    }

    override fun onDestroyView() {
        _ui = null
        super.onDestroyView()
    }

    private fun showProgressBar() {
        ui.usersListProgressBar.isVisible = true
    }

    private fun hideProgressBar() {
        ui.usersListProgressBar.isVisible = false
    }

}