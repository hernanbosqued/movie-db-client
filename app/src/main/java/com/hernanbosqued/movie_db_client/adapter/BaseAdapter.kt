package com.hernanbosqued.movie_db_client.adapter

import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.movie_db_client.BaseViewHolder
import com.hernanbosqued.movie_db_client.ScrollListener

abstract class BaseAdapter<M, VH : BaseViewHolder<M>>(private val listener: ScrollListener?) : RecyclerView.Adapter<VH>() {

    protected var entities: MutableList<M> = ArrayList()

    fun add(entity: M) {
        this.entities.add(0, entity)
        notifyItemInserted(0)
    }

    fun add(entities: List<M>) {
        this.entities.addAll(entities)
        notifyItemRangeInserted(this.entities.size - entities.size, entities.size)
    }

    fun indexOf(entity: M):Int {
        return entities.indexOf(entity)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(entities[position])
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}