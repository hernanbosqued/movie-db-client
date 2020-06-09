package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel

class CarouselPresenter( model: CarouselModel, view: CarouselContract.View)
    : BasePresenter<CarouselModel, CarouselContract.View>(model), CarouselContract.Presenter, ClientCallbacks<ListModel> {

    private var page = 0
    private var maxPages: Int = 0

    var areItemsRemainingToLoad: Boolean = true

    init {
        this.model.title = model.title
        bindView(view)
    }

    override fun load() {
        view()?.showProgress()
        model.client?.invoke(++page, model.query, this)
    }

    override fun onCarouselClicked() {
        view()?.showCarouselData(this.model.response)
    }

    override fun onOK(model: ListModel) {
        view()?.hideProgress()

        this.maxPages = model.totalPages
        this.areItemsRemainingToLoad = page <= maxPages
        this.model.response.results.addAll(model.results)

        view()?.addData(model)

        if (this.model.response.results.isEmpty()) {
            view()?.showEmpty()
        } else {
            view()?.hideEmpty()
        }
    }

    override fun onError(error: String) {
        view()?.showMessage(error)
        view()?.hideProgress()

        if (this.model.response.results.isEmpty()) {
            view()?.showEmpty()
        } else {
            view()?.hideEmpty()
        }
    }
}