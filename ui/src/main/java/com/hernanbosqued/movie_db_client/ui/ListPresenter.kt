package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.model.ListModel

class ListPresenter(view: ListContract.View, private val client: CarouselClient, private val stringRepository: StringRepository) : BasePresenter<ListModel, ListContract.View>(view), ListContract.Presenter {

    private val data = arrayOf<CarouselModel>(
        CarouselModel(endpoint = client::getPopularMoviesList, title = stringRepository.getA(), query = "a"),
        CarouselModel(endpoint = client::getPopularMoviesList, title = stringRepository.getB(), query = "b"),
        CarouselModel(endpoint = client::getPopularMoviesList, title = stringRepository.getC(), query = "c")
    )

    private fun addCarousel(carouselModel: CarouselModel) {
        view()?.addCarousel(carouselModel)
    }

    override fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean) {
        //TODO("Not yet implemented")
    }

    override fun bind() {
//        list.add(CarouselModel(Client::searchBoth, "both", "a"))
//        list.add(CarouselModel(Client::searchBoth, "With video", "Depeche Mode video singles"))
        //list.add(CarouselModel("popular movies", null))
//        list.add(CarouselModel(Cli    ent::getMoviesTopRated, "Top rated movies", null))
//        list.add(CarouselModel(Client::getTVPopular, "Popular TV shows", null))
//        list.add(CarouselModel(Client::getTVTopRated, "Top rated TV show", null))
        data.forEach { addCarousel(it) }
    }
}

