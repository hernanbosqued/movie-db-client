package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.CarouselClientCallbacks
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel

class CarouselPresenter(view: CarouselContract.View, var client: CarouselClient) : BasePresenter<CarouselModel, CarouselContract.View>(view), CarouselContract.Presenter, CarouselClientCallbacks {

    var page = 1

    override fun onOK(data: List<CarouselItemModel>) {
        view()?.hideProgress()

        this.model?.list?.addAll(data)

        view()?.addData(data)

        if (this.model?.list?.isEmpty()!!) {
            view()?.showEmpty()
        } else {
            view()?.hideEmpty()
        }
    }

    fun setModel(model: CarouselModel) {
        super.model = model
        model.list = ArrayList()
        view()?.setTitle(model.title)
        load()
    }

    override fun load() {
        view()?.showProgress()

        val c = client.javaClass.methods.find { it.name.contentEquals(model?.method!!) }
        c?.invoke(client, page++, model?.query, this)
    }

    override fun onCarouselClicked() {
        model?.let { view()?.showCarouselData(it) }
    }

    override fun onError(error: String) {
        view()?.showMessage(error)
        view()?.hideProgress()
    }
}