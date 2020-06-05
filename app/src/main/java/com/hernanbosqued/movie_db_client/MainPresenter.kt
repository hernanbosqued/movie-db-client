package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.RepositoryImpl

class MainPresenter : BasePresenter<MutableList<ResultModel>, MainContract.View>(mutableListOf()), MainContract.Presenter {

    override fun processQuery(query: String) {
        view()?.showProgress()
        //client.getList(0, this)
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