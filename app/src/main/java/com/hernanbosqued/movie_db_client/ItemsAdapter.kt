package com.hernanbosqued.movie_db_client

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.ITEM_TYPE.Companion.valueOf

internal class ItemsAdapter : BaseAdapter<ResultModel, ItemViewHolder>() {
    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(view, valueOf(viewType))
    }
}