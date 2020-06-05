package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel

class CarouselPresenter(val client: (Int, ClientCallbacks) -> Unit) : BasePresenter<MutableList<ResultModel>, CarouselContract.View>(mutableListOf()), CarouselContract.Presenter, ClientCallbacks {
    private var page = 1

    override fun load() {
        if (model.isEmpty()) {
            page = 1
            view()?.showProgress()
            client(page, this)
        }
    }

    override fun loadMore() {
        view()?.showProgress()
        client(++page, this)
    }

    override fun bindView(view: CarouselContract.View) {
        super.bindView(view)

        if (model.isNotEmpty()) {
            updateView()
        }
    }

    override fun onOK(model: ListModel) {
        view()?.hideProgress()

        model.results?.let{
            this.model.addAll(it)
            view()?.showItems(it)
        }

        if (this.model.isEmpty()) {
            view()?.showEmpty()
        } else {
            view()?.hideEmpty()
        }
    }

    override fun onError(error: String) {
        view()?.showMessage(error)
        view()?.hideProgress()
    }

    override fun updateView() {
        view()?.showItems(model)
    }
}