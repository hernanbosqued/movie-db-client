package com.hernanbosqued.movie_db_client

import android.widget.CheckBox
import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.RepositoryImpl

class MainPresenter : BasePresenter<MutableList<ResultModel>, MainContract.View>(mutableListOf()), MainContract.Presenter {

    override fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean) {
        if( includeMovies && includeTVShows){
            view()?.addCarousel(Client::searchBoth, "Movies and TV shows for '$query'", query)
        }else if(includeTVShows){
            view()?.addCarousel(Client::searchTVShows, "TV shows for '$query'", query)
        }else{
            view()?.addCarousel(Client::searchMovies, "Movies for '$query'", query)
        }
    }

    override fun prepareLists() {
        Client.repo = RepositoryImpl

        view()?.addCarousel(Client::getMoviesPopular, "Popular movies")
        view()?.addCarousel(Client::getMoviesTopRated, "Top rated movies")
        view()?.addCarousel(Client::getTVPopular, "Popular TV shows")
        view()?.addCarousel(Client::getTVTopRated, "Top rated TV shows")
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