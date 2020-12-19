package com.hernanbosqued.movie_db_client.ui.presenter

import com.hernanbosqued.movie_db_client.domain.*
import com.hernanbosqued.movie_db_client.ui.CarouselClient
import com.hernanbosqued.movie_db_client.ui.contract.DetailContract
import javax.inject.Inject

class DetailPresenter @Inject constructor(view: DetailContract.View) :
    BasePresenter<CarouselItemModel, DetailContract.View>(CarouselItemModel(), view), DetailContract.Presenter, RepositoryCallback<ListModel<VideoResultModel>> {

    private val client = CarouselClient()

    override fun start() {
        view().setTitle(model().title!!)
        view().setPoster(model().path)
        view().setOverview(model().overview!!)
        view().setRanking(model().ranking!!)

        if (model().hasVideo) {
            view().hideEmpty()
            view().showVideo()
            client.videos(model().type!!, model().id, this)
        } else {
            view().showEmpty()
        }
    }

    override fun onSuccess(data: ListModel<VideoResultModel>) {
        view().setVideo(data.results!!.first())
    }

    override fun onFail(error: ErrorModel) {
        view().showMessage(error.code.toString() + " - " + error.message)
    }
}
