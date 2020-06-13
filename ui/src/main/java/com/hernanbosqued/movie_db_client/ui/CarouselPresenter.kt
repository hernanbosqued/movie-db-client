package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.ClientCallbacks
import com.hernanbosqued.movie_db_client.domain.model.ListModel

class CarouselPresenter(view: CarouselContract.View) : BasePresenter<CarouselModel, CarouselContract.View>(view), CarouselContract.Presenter, ClientCallbacks<ListModel> {

    private var page = 1
    private var maxPages: Int = 0
    private var continueLoading: Boolean = true

    fun setModel(model: CarouselModel) {
        this.model = model
        view()?.setTitle(model.title)
        load(page)
    }

    private fun load(page: Int) {
        view()?.showProgress()
        model?.let { it.client.invoke(page, it.query, this) }
    }

    override fun load() {
        if (continueLoading) {
            load(++page)
        }
    }

    override fun onCarouselClicked() {
        model?.let { view()?.showCarouselData(it) }
    }

    override fun onOK(response: ListModel) {
        view()?.hideProgress()

        this.maxPages = response.totalPages
        this.continueLoading = page < maxPages
        val filteredResult = response.results.filterNotNull()
        this.model?.response?.results?.addAll(filteredResult)

        view()?.addData(filteredResult)

        if (this.model?.response?.results?.isEmpty()!!) {
            view()?.showEmpty()
        } else {
            view()?.hideEmpty()
        }
    }

    override fun onError(error: String) {
        view()?.showMessage(error)
        view()?.hideProgress()

        view()?.showEmpty(error)
    }
}