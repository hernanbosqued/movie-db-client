package com.hernanbosqued.movie_db_client

class CarouselViewHolder(val view: CarouselView) : BaseViewHolder<CarouselModel>(view) {

    override fun bind(model: CarouselModel) {
        view.bind(model)
    }
}