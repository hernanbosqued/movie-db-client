package com.hernanbosqued.movie_db_client

import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.domain.ClickCallback
import com.hernanbosqued.domain.model.ResultModel
import kotlin.collections.ArrayList

abstract class BaseAdapter<M, VH : BaseViewHolder<M>> : RecyclerView.Adapter<VH>() {
    val clickCallback: ClickCallback<ResultModel>? = null

    var reachLastItemListener: ReachLastItemListener? = null

    private var entities: MutableList<M> = ArrayList()

    fun setData(entities: List<M>) {
        this.entities.addAll(entities)
        notifyItemRangeInserted(this.entities.size - entities.size, entities.size)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(entities[position])
        if (position == entities.size - 1) {
            reachLastItemListener?.onLastItemReached()
        }
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}