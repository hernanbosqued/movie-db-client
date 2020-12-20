package com.hernanbosqued.movie_db_client.ui.presenter

import com.hernanbosqued.movie_db_client.domain.CarouselClientCallback
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.CarouselService
import com.hernanbosqued.movie_db_client.ui.contract.CarouselContract
import javax.inject.Inject

class CarouselPresenter @Inject constructor(view: CarouselContract.View, private val service: CarouselService, private val resources: ResourcesRepository) :
    BasePresenter<CarouselModel, CarouselContract.View>(CarouselModel(), view), CarouselContract.Presenter, CarouselClientCallback {

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
            view().setInfo(title, page, totalPages, totalResults, results)
            checkListContent()
        }
    }

    override fun load(first: Boolean) {
        model().let { model ->
            if (model.info) {
                view().showInfo()
            } else {
                view().hideInfo()
            }

            if (first || model.page < model.totalPages) {
                val method = service.javaClass.methods.filterNotNull().find {
                    model.method.contentEquals(it.name)
                }
                view().showProgress()
                method?.invoke(service, model.page + 1, model.query, this)
            }
        }
    }

    override fun onCarouselClicked() {
        model().let { view().showCarouselData(it) }
    }

    override fun onError(error: String) {
        view().hideProgress()
        view().hideInfo()
        view().showMessage(error)
        checkListContent()
    }

    private fun checkListContent() {
        if (model().list.isNullOrEmpty()) {
            view().hideInfo()
            view().showMessage(resources.noResultsFound())
        } else
            view().hideMessage()
    }
}