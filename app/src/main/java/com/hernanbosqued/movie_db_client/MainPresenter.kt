package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.MoviesClient
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.repo.MoviesRepositoryImpl

class MainPresenter : BasePresenter<MutableList<ResultModel>, MainContract.View>(mutableListOf()), MainContract.Presenter, MoviesClient.Callback {
    private var client: MoviesClient = MoviesClient(MoviesRepositoryImpl)
    private var isLoading = false
    private var isEmpty = false
    private var page = 1

    override fun processQuery(query: String) {
        isLoading = true
        view()?.showProgress()
        client.getList(page, this)
    }

    override fun loadMore() {
        view()?.showProgress()
        client.getList(++page, this)
    }

    override fun updateView() {
        view()?.showItems(model)
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
        }
        processQuery("")
    }

    override fun onOK(model: ListModel) {
        isLoading = false
        this.model.addAll(model.results)
        updateView()
        view()?.hideProgress()

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