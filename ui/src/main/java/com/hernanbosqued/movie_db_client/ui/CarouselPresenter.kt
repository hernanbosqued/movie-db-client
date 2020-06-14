package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.CarouselClientCallbacks
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel

class CarouselPresenter(view: CarouselContract.View) : BasePresenter<CarouselModel, CarouselContract.View>(view), CarouselContract.Presenter, CarouselClientCallbacks {

    override fun onOK(data: CarouselItemModel) {
        view()?.hideProgress()

        this.model?.list?.add(data)

        view()?.addData(data)

        if (this.model?.list?.isEmpty()!!) {
            view()?.showEmpty()
        } else {
            view()?.hideEmpty()
        }
    }

    fun setModel(model: CarouselModel) {
        super.model = model
        view()?.setTitle(model.title)
        load()
    }

    override fun load() {
        view()?.showProgress()
        model?.endpoint?.invoke(model?.query, this)
    }

    override fun onCarouselClicked() {
        model?.let { view()?.showCarouselData(it) }
    }

//    override fun onOK(data: List<CarouselItemModel>) {
//        view()?.hideProgress()
//
//        //this.maxPages = response.totalPages
//        //this.continueLoading = page < maxPages
//        val filteredResult = data.filterNotNull()
//        this.model?.list?.addAll(filteredResult)
//
//        view()?.addData(filteredResult)
//
//        if (this.model?.list?.isEmpty()!!) {
//            view()?.showEmpty()
//        } else {
//            view()?.hideEmpty()
//        }
//    }

     override fun onError(error: String) {
        view()?.showMessage(error)
        view()?.hideProgress()
    }
}