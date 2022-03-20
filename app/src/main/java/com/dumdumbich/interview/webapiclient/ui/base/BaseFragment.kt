package com.dumdumbich.interview.webapiclient.ui.base

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment


open class BaseFragment : Fragment() {

    private val TAG = "@@@  ${this::class.java.simpleName} : ${this.hashCode()}"


    override fun onAttach(context: Context) {
        Log.d(TAG, "onAttach() called with: context = $context")
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate() called with: savedInstanceState = $savedInstanceState")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(
            TAG,
            "onCreateView() called with: inflater = $inflater, container = $container, savedInstanceState = $savedInstanceState"
        )
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(
            TAG,
            "onViewCreated() called with: view = $view, savedInstanceState = $savedInstanceState"
        )
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onStart() {
        Log.d(TAG, "onStart() called")
        super.onStart()
    }

    override fun onResume() {
        Log.d(TAG, "onResume() called")
        super.onResume()
    }

    override fun onPause() {
        Log.d(TAG, "onPause() called")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop() called")
        super.onStop()
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView() called")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy() called")
        super.onDestroy()
    }

}