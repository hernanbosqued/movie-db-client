package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.CarouselModel

class ListContract {
    interface View {
        fun showMessage(message: String)
        fun addCarousel(model: CarouselModel)
        fun scrollTop()
    }

    interface Presenter {
        fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean)
        fun bind()
    }
}