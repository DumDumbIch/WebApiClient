package com.dumdumbich.interview.webapiclient.ui.screen.user

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dumdumbich.interview.webapiclient.databinding.ItemRepositoryBriefInfoBinding
import com.dumdumbich.interview.webapiclient.domain.entity.Repository


class ItemsListAdapter(
    private var data: List<Repository>,
    private val listener: ItemActionListener
) : RecyclerView.Adapter<ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            ItemRepositoryBriefInfoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            listener
        )

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<Repository>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size

    private fun getItem(position: Int): Repository = data[position]

}