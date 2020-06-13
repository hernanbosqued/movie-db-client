package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.Client
import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.model.ListModel

class ListPresenter(val view: ListContract.View, repository: Repository) : BasePresenter<ListModel, ListContract.View>(view), ListContract.Presenter {

    init {
        Client.repo = repository
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
    }

    override fun addCarousels() {

        val list: MutableList<CarouselModel> = ArrayList()
        list.add(CarouselModel(Client::searchMovies, "With video", "Depeche Mode video singles"))
        list.add(CarouselModel(Client::getMoviesPopular, "popular movies", null))
        list.add(CarouselModel(Client::getMoviesTopRated, "Top rated movies", null))
        list.add(CarouselModel(Client::getTVPopular, "Popular TV shows", null))
        list.add(CarouselModel(Client::getTVTopRated, "Top rated TV show", null))

        view()?.addCarousels(list)
    }
}