package com.hernanbosqued.movie_db_client

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hernanbosqued.domain.model.ResultModel

class ItemsAdapter(private val listener: ItemClickListener) : BaseAdapter<ResultModel, ItemViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(view, ITEM_TYPE.valueOf(viewType),listener)
    }
}