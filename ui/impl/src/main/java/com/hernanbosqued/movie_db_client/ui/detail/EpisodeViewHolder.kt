package com.hernanbosqued.movie_db_client.ui.detail

import android.view.View
import com.hernanbosqued.movie_db_client.domain.EpisodeModel
import com.hernanbosqued.movie_db_client.ui.BaseViewHolder
import kotlinx.android.synthetic.main.layout_episode.view.*

class EpisodeViewHolder(view: View) : BaseViewHolder<EpisodeModel>(view) {
    override fun bind(model: EpisodeModel) {
        itemView.episode.text = model.name
    }
}