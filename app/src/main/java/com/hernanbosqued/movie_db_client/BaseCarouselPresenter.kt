package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel

abstract class BaseCarouselPresenter : BasePresenter<MutableList<ResultModel>, CarouselContract.View>(mutableListOf()), CarouselContract.Presenter, ClientCallbacks<ListModel> {

    abstract override fun load()

    abstract override fun loadMore()

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