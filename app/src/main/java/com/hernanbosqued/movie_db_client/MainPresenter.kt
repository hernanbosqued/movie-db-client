package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.repo.RepositoryImpl

class MainPresenter(val view: MainContract.View) : BasePresenter<ListModel, MainContract.View>(ListModel()), MainContract.Presenter {

    init {
        bindView(view)
    }

    override fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean) {
        val carousel =
            if (includeMovies && includeTVShows) {
                CarouselModel(Client::searchBoth, "Movies and TV shows for '$query'", query)
            } else if (includeTVShows) {
                CarouselModel(Client::searchTVShows, "TV shows for '$query'", query)
            } else {
                CarouselModel(Client::searchMovies, "Movies for '$query'", query)
            }

        view()?.addCarousel(carousel)
        view()?.scrollTop()
    }

    override fun addCarousels() {
        Client.repo = RepositoryImpl

        val list: MutableList<CarouselModel> = ArrayList()
        list.add(CarouselModel(Client::getMoviesPopular, "popular movies", null))
        list.add(CarouselModel(Client::getMoviesTopRated, "Top rated movies", null))
        list.add(CarouselModel(Client::getTVPopular, "Popular TV shows", null))
        list.add(CarouselModel(Client::getTVTopRated, "Top rated TV shows", null))

        list.forEach { carousel ->
            view()?.addCarousel(carousel)
        }

        view()?.scrollTop()
    }
}