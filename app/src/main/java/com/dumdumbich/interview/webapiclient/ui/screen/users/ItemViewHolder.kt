package com.dumdumbich.interview.webapiclient.ui.screen.users

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.dumdumbich.interview.webapiclient.databinding.ItemUserBriefInfoBinding
import com.dumdumbich.interview.webapiclient.domain.entity.User


class ItemViewHolder(
    private val ui: ItemUserBriefInfoBinding,
    private val listener: ItemActionListener
) : RecyclerView.ViewHolder(ui.root) {

    private val TAG = "@@@  ${this::class.java.simpleName} : ${this.hashCode()}"

    private var item: User? = null


    fun bind(item: User) {
        this.item = item
        ui.itemUserLoginTextView.text = item.login
        ui.itemUserRepositoryUrlTextView.text = item.reposUrl
        itemView.setOnClickListener { listener.onItemShortClickListener(item) }
        itemView.setOnLongClickListener { listener.onItemLongClickListener(item, ui.root) }
        Log.d(TAG, "bind() called with: item = { ${item.login} : ${item.reposUrl}}")
    }

}