package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.CarouselClientCallbacks
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.presenter.BasePresenter
import com.hernanbosqued.movie_db_client.presenter.CarouselContract

class CarouselPresenter(view: CarouselContract.View, private var client: CarouselClient) :
    BasePresenter<CarouselModel, CarouselContract.View>(view),
    CarouselContract.Presenter,
    CarouselClientCallbacks {

    override fun onOK(model: CarouselModel) {
        view()?.hideProgress()
        updateModel(model)
    }

    private fun updateModel(param: CarouselModel) {
        this.model()?.apply {
            title = param.title
            page = param.page
            totalPages = param.totalPages
            totalResults = param.totalResults
            list.addAll(param.list)
            results = list.size

            view()?.addData(param.list)
            view()?.setCarouselInfo(title, page, totalPages, totalResults, results)

            checkListContent()
        }
    }

    fun setModel(model: CarouselModel) {
        super.bindModel(model)
        load(true)
    }

    override fun load(first: Boolean) {
        model()?.let { model ->
            if (first || model.page < model.totalPages) {
                val method = client.javaClass.methods.find { it.name.contentEquals(model.method!!) }
                view()?.showProgress()
                method?.invoke(client, model.page + 1, model.query, this)
            }
        }
    }

    override fun onCarouselClicked() {
        model()?.let { view()?.showCarouselData(it) }
    }

    override fun onError(error: String) {
        view()?.hideProgress()
        view()?.showEmpty(error)
        checkListContent()
    }

    private fun checkListContent() {
        if (model()?.list.isNullOrEmpty()) {
            view()?.showEmpty()
        } else
            view()?.hideEmpty()
    }
}
