package com.dumdumbich.interview.webapiclient.ui.screen.users

import android.view.View
import com.dumdumbich.interview.webapiclient.domain.entity.User


interface ItemActionListener {
    fun onItemShortClickListener(user: User)
    fun onItemLongClickListener(user: User, anchor: View): Boolean
}