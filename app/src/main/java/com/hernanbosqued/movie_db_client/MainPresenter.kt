package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.MoviesClient
import com.hernanbosqued.domain.TVClient
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.RepositoryImpl

class MainPresenter : BasePresenter<MutableList<ResultModel>, MainContract.View>(mutableListOf()), MainContract.Presenter {

    override fun processQuery(query: String) {
        view()?.showProgress()
        //client.getList(0, this)
    }

    override fun prepareLists() {
        view()?.addCarousel(MoviesClient(RepositoryImpl), "Peliculas Populares")
        view()?.addCarousel(TVClient(RepositoryImpl), "Series Populares")
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