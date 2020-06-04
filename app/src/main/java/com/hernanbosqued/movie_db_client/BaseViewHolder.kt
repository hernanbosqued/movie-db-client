package com.hernanbosqued.movie_db_client

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

abstract class BaseViewHolder<M>(itemView: View) : ViewHolder(itemView) {
    abstract fun bind(model: M)
}