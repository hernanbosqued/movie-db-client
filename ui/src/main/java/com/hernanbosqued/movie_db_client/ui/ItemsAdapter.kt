package com.hernanbosqued.movie_db_client.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.movie_db_client.domain.model.ResultModel

class ItemsAdapter(private val carouselListener: ScrollListener, private val listener: ItemListener): RecyclerView.Adapter<ItemViewHolder>() {

    private var entities: MutableList<ResultModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
        return ItemViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(entities[position])

        if (position == entities.size - 1) {
            carouselListener.onLastItemReached()
        }
    }

    fun add(entities: List<ResultModel>) {
        this.entities.addAll(entities)
        notifyItemRangeInserted(this.entities.size - entities.size, entities.size)
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}