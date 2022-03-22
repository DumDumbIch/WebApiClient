package com.dumdumbich.interview.webapiclient.ui.screen.user

import android.view.View
import com.dumdumbich.interview.webapiclient.domain.entity.Repository


interface ItemActionListener {
    fun onItemShortClickListener(repository: Repository)
    fun onItemLongClickListener(repository: Repository, anchor: View): Boolean
}