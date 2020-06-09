package com.hernanbosqued.movie_db_client

class MainContract {
    interface View {
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
        fun addCarousel(model: CarouselModel)
        fun scrollTop()
    }

    interface Presenter {
        fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean)
        fun addCarousels()
    }
}