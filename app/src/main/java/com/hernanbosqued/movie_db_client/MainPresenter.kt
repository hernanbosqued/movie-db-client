package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.RepositoryImpl

class MainPresenter : BasePresenter<MutableList<ResultModel>, MainContract.View>(mutableListOf()), MainContract.Presenter {

    override fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean) {
        if( includeMovies && includeTVShows){
            view()?.addSearchCarousel(Client::searchBoth, "Movies and TV shows for '$query'", query)
        }else if(includeTVShows){
            view()?.addSearchCarousel(Client::searchTVShows, "TV shows for '$query'", query)
        }else{
            view()?.addSearchCarousel(Client::searchMovies, "Movies for '$query'", query)
        }
    }

    override fun prepareLists() {
        Client.repo = RepositoryImpl

        view()?.addViewCarousel(Client::getMoviesPopular, "Popular movies")
        view()?.addViewCarousel(Client::getMoviesTopRated, "Top rated movies")
        view()?.addViewCarousel(Client::getTVPopular, "Popular TV shows")
        view()?.addViewCarousel(Client::getTVTopRated, "Top rated TV shows")
        model.add(ResultModel())
    }

    override fun updateView() {
    }

    override fun bindView(view: MainContract.View) {
        super.bindView(view)

        if (model.isEmpty()) {
            prepareLists()
        }
    }
}