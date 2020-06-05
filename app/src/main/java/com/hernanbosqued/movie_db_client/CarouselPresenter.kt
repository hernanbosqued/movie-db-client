package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel

class CarouselPresenter : BasePresenter<MutableList<ResultModel>, CarouselContract.View>, CarouselContract.Presenter, ClientCallbacks<ListModel> {
    private var page = 1
    private var query = ""
    private var clientView: ((Int, ClientCallbacks<ListModel>) -> Unit)? = null
    private var clientSearch: ((Int, String, ClientCallbacks<ListModel>) -> Unit)? = null

    constructor(client: (Int, ClientCallbacks<ListModel>) -> Unit) : super(mutableListOf()) {
        this.clientView = client
    }

    constructor(client: (Int, String, ClientCallbacks<ListModel>) -> Unit, query: String) : super(mutableListOf()) {
        this.clientSearch = client
        this.query = query
    }

    override fun load() {
        if (model.isEmpty()) {
            page = 1
            view()?.showProgress()
            clientView?.let { it(page, this) }
            clientSearch?.let { it(page, query, this) }
        }
    }

    override fun loadMore() {
        view()?.showProgress()
        clientView?.let { it(++page, this) }
        clientSearch?.let { it(++page, query, this) }    }

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