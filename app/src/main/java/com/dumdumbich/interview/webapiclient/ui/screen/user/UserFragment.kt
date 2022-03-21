package com.dumdumbich.interview.webapiclient.ui.screen.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dumdumbich.interview.webapiclient.databinding.FragmentUserBinding
import com.dumdumbich.interview.webapiclient.ui.base.BaseFragment


class UserFragment : BaseFragment() {

    private var _ui: FragmentUserBinding? = null
    private val ui get() = _ui!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentUserBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _ui = FragmentUserBinding.bind(view)
    }

    override fun onDestroyView() {
        _ui = null
        super.onDestroyView()
    }

}