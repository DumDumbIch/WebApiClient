package com.dumdumbich.interview.webapiclient.ui.screen.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dumdumbich.interview.webapiclient.databinding.FragmentListBinding
import com.dumdumbich.interview.webapiclient.ui.base.BaseFragment


class ListFragment : BaseFragment() {

    private var _ui: FragmentListBinding? = null
    private val ui get() = _ui!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentListBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _ui = FragmentListBinding.bind(view)
    }

    override fun onDestroyView() {
        _ui = null
        super.onDestroyView()
    }

}