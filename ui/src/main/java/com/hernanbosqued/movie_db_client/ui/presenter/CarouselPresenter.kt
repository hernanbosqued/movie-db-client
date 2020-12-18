package com.hernanbosqued.movie_db_client.ui.presenter

import com.hernanbosqued.movie_db_client.domain.CarouselClientCallback
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.ui.CarouselClient
import com.hernanbosqued.movie_db_client.ui.contract.CarouselContract

class CarouselPresenter(
    view: CarouselContract.View,
    model: CarouselModel,
    private var client: CarouselClient
) :
    BasePresenter<CarouselModel, CarouselContract.View>(model, view),
    CarouselContract.Presenter,
    CarouselClientCallback {

    init {
        load(true)
    }

    override fun onOK(model: CarouselModel) {
        view().hideProgress()
        updateModel(model)
    }

    private fun updateModel(param: CarouselModel) {
        this.model().apply {
            title = param.title.format(param.query)
            page = param.page
            totalPages = param.totalPages
            totalResults = param.totalResults
            list.addAll(param.list)
            results = list.size

            view().addData(param.list)
            view().setCarouselInfo(title, page, totalPages, totalResults, results)

            checkListContent()
        }
    }

    override fun load(first: Boolean) {
        model().let { model ->
            if (first || model.page < model.totalPages) {
                val method = client.javaClass.methods.filterNotNull().find {
                    model.method.contentEquals(it.name)
                }
                view().showProgress()
                method?.invoke(client, model.page + 1, model.query, this)
            }
        }
    }

    override fun onCarouselClicked() {
        model().let { view().showCarouselData(it) }
    }

    override fun onError(error: String) {
        view().hideProgress()
        view().showEmpty(error)
        checkListContent()
    }

    private fun checkListContent() {
        if (model().list.isNullOrEmpty()) {
            view().showEmpty()
        } else
            view().hideEmpty()
    }
}
