package com.hernanbosqued.movie_db_client.adapter

import android.view.ViewGroup
import com.hernanbosqued.movie_db_client.*

class CarouselsAdapter(private val listener: CarouselListeners) : BaseAdapter<CarouselModel, CarouselViewHolder>(null) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        return CarouselViewHolder(CarouselView(parent.context, listener))
    }
}
