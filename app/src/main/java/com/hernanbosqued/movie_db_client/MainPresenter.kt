package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.MoviesClient
import com.hernanbosqued.domain.TVClient
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.RepositoryImpl

class MainPresenter : BasePresenter<MutableList<ResultModel>, MainContract.View>(mutableListOf()), MainContract.Presenter {
    private var client: MoviesClient = MoviesClient(RepositoryImpl)
    private var isLoading = false
    private var isEmpty = false

    override fun processQuery(query: String) {
        isLoading = true
        view()?.showProgress()
        //client.getList(0, this)
    }

    override fun prepareLists() {
        view()?.addCarousel(MoviesClient(RepositoryImpl), "Peliculas Populares")
        view()?.addCarousel(TVClient(RepositoryImpl), "Series Populares")
    }

    override fun updateView() {
    }

    override fun bindView(view: MainContract.View) {
        super.bindView(view)
        if (isEmpty) {
            view()?.showEmpty()
        }
        if (isLoading) {
            view()?.showProgress()
        }
        if (model.isNotEmpty()) {
            updateView()
        }else{
            prepareLists()
        }
    }

//    override fun onOK(model: ListModel) {
//        isLoading = false
//        view()?.hideProgress()
//
//        this.model.addAll(model.results)
//        view()?.showItems(model.results)
//
//        if (this.model.isEmpty()) {
//            isEmpty = true
//            view()?.showEmpty()
//        } else {
//            isEmpty = false
//            view()?.hideEmpty()
//        }
//    }
//
//    override fun onError(error: String) {
//        isLoading = false
//        view()?.showMessage(error)
//        view()?.hideProgress()
//    }
}