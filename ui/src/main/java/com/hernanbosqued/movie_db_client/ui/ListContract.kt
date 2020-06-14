package com.hernanbosqued.movie_db_client.ui

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