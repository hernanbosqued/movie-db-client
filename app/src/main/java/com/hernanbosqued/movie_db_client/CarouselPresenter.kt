package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel

class CarouselPresenter(private val client: Client) : BasePresenter<MutableList<ResultModel>, CarouselContract.View>(mutableListOf()), CarouselContract.Presenter, ClientCallbacks {
    private var isLoading = false
    private var isEmpty = false
    private var page = 1

    override fun load() {
        view()?.showProgress()
        client.get(page, this)
    }

    override fun loadMore() {
        view()?.showProgress()
        client.get(++page, this)
    }

    override fun updateView() {
        view()?.showItems(model)
    }

    override fun bindView(view: CarouselContract.View) {
        super.bindView(view)
        if (isEmpty) {
            view()?.showEmpty()
        }
        if (isLoading) {
            view()?.showProgress()
        }
        if (model.isNotEmpty()) {
            updateView()
        }
        //client.getList(page, this)
        //client.getMoviesPopular(page, this)    }
    }

    override fun onOK(model: ListModel) {
        isLoading = false
        view()?.hideProgress()

        this.model.addAll(model.results)
        view()?.showItems(model.results)

        if (this.model.isEmpty()){
            isEmpty = true
            view()?.showEmpty()
        } else {
            isEmpty = false
            view()?.hideEmpty()
        }
    }

    override fun onError(error: String) {
        isLoading = false
        view()?.showMessage(error)
        view()?.hideProgress()
    }
}