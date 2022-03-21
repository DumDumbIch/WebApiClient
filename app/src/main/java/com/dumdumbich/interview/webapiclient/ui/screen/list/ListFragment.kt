package com.dumdumbich.interview.webapiclient.ui.screen.list

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.dumdumbich.interview.webapiclient.app
import com.dumdumbich.interview.webapiclient.databinding.FragmentListBinding
import com.dumdumbich.interview.webapiclient.domain.entity.User
import com.dumdumbich.interview.webapiclient.ui.base.BaseFragment
import java.lang.IllegalStateException


class ListFragment : BaseFragment() {

    private var _ui: FragmentListBinding? = null
    private val ui get() = _ui!!

    private val handlerThread: HandlerThread = HandlerThread("thread_pool")
    private val uiHandler by lazy { Handler(Looper.getMainLooper()) }
    private val jobHandler by lazy { Handler(handlerThread.looper) }

    private lateinit var itemsListAdapter: ItemsListAdapter
    private val users = ArrayList<User>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentListBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _ui = FragmentListBinding.bind(view)
        handlerThread.start()

        itemsListAdapter = ItemsListAdapter(users)
        ui.briefInfoItemRecyclerView.adapter = itemsListAdapter

        showProgressBar()

        app.githubDataSource.getUsers(
            onSuccess = { users ->
                this.users.clear()
                this.users.addAll(users)
                uiHandler.post {
                    itemsListAdapter.notifyDataSetChanged()
                    hideProgressBar()
                }
            },
            onError = {
                throw IllegalStateException("GitHub: data receive error")
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