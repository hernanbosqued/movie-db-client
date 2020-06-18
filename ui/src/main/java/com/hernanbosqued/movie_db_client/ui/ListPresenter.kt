package com.hernanbosqued.movie_db_client.ui

import com.google.gson.Gson
import com.hernanbosqued.movie_db_client.domain.*

class ListPresenter(view: ListContract.View, var repository: Repository) : BasePresenter<List<CarouselModel>, ListContract.View>(view), ListContract.Presenter, RepositoryCallbacks<CarouselListModel> {

//    private val data_ = arrayOf(
//        CarouselModel(endpoint = client::searchBoth, title = stringRepository.searchBoth(), query = "a"),
//        CarouselModel(endpoint = client::searchTV, title = stringRepository.searchTV(), query = "b"),
//        CarouselModel(endpoint = client::searchMovies, title = stringRepository.searchMovies(), query = "c"),
//        CarouselModel(endpoint = client::tvPopular, title = "Popular TV shows", query = null),
//        CarouselModel(endpoint =  Repository::searchBoth, title = "With video", query = "Depeche Mode video singles")
//        CarouselModel(endpoint = client::moviesPopular, title = "popular movies", query = null),
//        CarouselModel(endpoint = client::moviesTopRated, title = "Top rated movies", query = null),
//        CarouselModel(endpoint = client::tvTopRated, title = "Top rated TV show", query = null)
//    )

    private fun addCarousel(carouselModel: CarouselModel) {
        view()?.addCarousel(carouselModel)
    }

    override fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean) {
        //TODO("Not yet implemented")
    }

    override fun bind() {
        repository.carouselList(this)
    }

    override fun onSuccess(data: CarouselListModel) {
        data.results.forEach{ view()?.addCarousel(it)}
    }

    override fun onFail(error: ErrorModel) {
        view()?.showMessage(error.message)
    }
}

