package com.hernanbosqued.movie_db_client.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.*

class ItemsAdapter(private val carouselListener: ScrollListener, private val listener: ItemListener) : BaseAdapter<ResultModel, ItemViewHolder>(carouselListener) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        if (position == entities.size - 1) {
            carouselListener.onLastItemReached()
        }
    }
}