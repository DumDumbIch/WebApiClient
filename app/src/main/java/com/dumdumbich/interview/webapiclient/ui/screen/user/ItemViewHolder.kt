package com.dumdumbich.interview.webapiclient.ui.screen.user

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.dumdumbich.interview.webapiclient.databinding.ItemRepositoryBriefInfoBinding
import com.dumdumbich.interview.webapiclient.domain.entity.Repository


class ItemViewHolder(
    private val ui: ItemRepositoryBriefInfoBinding,
    private val listener: ItemActionListener
) : RecyclerView.ViewHolder(ui.root) {

    private val TAG = "@@@  ${this::class.java.simpleName} : ${this.hashCode()}"

    private var item: Repository? = null


    @SuppressLint("SetTextI18n")
    fun bind(item: Repository) {
        this.item = item
        ui.itemRepositoryNameTextView.text = item.name
        ui.itemRepositoryForksTextView.text = "Forks : ${item.forksNumber.toString()}"
        itemView.setOnClickListener { listener.onItemShortClickListener(item) }
        itemView.setOnLongClickListener { listener.onItemLongClickListener(item, ui.root) }
        Log.d(TAG, "bind() called with: item = {${item.name} : ${item.forksNumber.toString()}}")
    }

}