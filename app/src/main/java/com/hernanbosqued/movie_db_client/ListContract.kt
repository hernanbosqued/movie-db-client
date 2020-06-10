package com.hernanbosqued.movie_db_client

class ListContract {
    interface View {
        fun showMessage(message: String)
        fun addCarousel(model: CarouselModel)
        fun addCarousels(models: List<CarouselModel>)
        fun scrollTop()
    }

    interface Presenter {
        fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean)
        fun addCarousels()
    }
}