package com.hernanbosqued.movie_db_client.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hernanbosqued.movie_db_client.domain.EpisodeModel
import com.hernanbosqued.movie_db_client.ui.R

class EpisodesAdapter : RecyclerView.Adapter<EpisodeViewHolder>() {

    private var entities: MutableList<EpisodeModel> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_episode, parent, false)
        return EpisodeViewHolder(view)
    }

    override fun onBindViewHolder(holder: EpisodeViewHolder, position: Int) {
        holder.bind(entities[position])
    }

    fun replace(entities: List<EpisodeModel>) {
        this.entities.clear()
        this.entities.addAll(entities)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return entities.size
    }
}