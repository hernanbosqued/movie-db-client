package com.hernanbosqued.movie_db_client

import androidx.recyclerview.widget.RecyclerView
import java.util.*

internal abstract class BaseAdapter<M, VH : BaseViewHolder<M>?> : RecyclerView.Adapter<VH>() {

    var listener: BaseAdapterListener? = null

    private var entities: List<M> = ArrayList()

    fun setData(entities: List<M>) {
        this.entities = entities
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder!!.bind(entities[position])
        if (position == entities.size - 1) {
            listener?.onBottom()
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}