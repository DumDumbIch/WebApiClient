package com.dumdumbich.interview.webapiclient.ui.screen.list

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.dumdumbich.interview.webapiclient.databinding.ItemUserBriefInfoBinding
import com.dumdumbich.interview.webapiclient.domain.entity.User


class ItemViewHolder(
    private val ui: ItemUserBriefInfoBinding
) : RecyclerView.ViewHolder(ui.root) {

    private val TAG = "@@@  ${this::class.java.simpleName} : ${this.hashCode()}"

    private var item: User? = null


    fun bind(item: User) {
        this.item = item
        ui.userLoginTextView.text = item.login
        ui.userRepositoryUrlTextView.text = item.reposUrl
        Log.d(TAG, "bind() called with: item = { ${item.login} : ${item.reposUrl}}")
    }

}