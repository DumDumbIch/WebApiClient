package com.dumdumbich.interview.webapiclient.ui.screen.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dumdumbich.interview.webapiclient.databinding.FragmentDetailBinding
import com.dumdumbich.interview.webapiclient.ui.base.BaseFragment


class DetailFragment : BaseFragment() {

    private var _ui: FragmentDetailBinding? = null
    private val ui get() = _ui!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentDetailBinding.inflate(inflater, container, false).root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _ui = FragmentDetailBinding.bind(view)
    }

    override fun onDestroyView() {
        _ui = null
        super.onDestroyView()
    }

}