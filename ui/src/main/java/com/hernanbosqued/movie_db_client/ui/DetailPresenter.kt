package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.*

class DetailPresenter(view: DetailContract.View, private val client: CarouselClient) : BasePresenter<CarouselItemModel, DetailContract.View>(view),
    DetailContract.Presenter, RepositoryCallbacks<ListModel<VideoResultModel>> {

    override fun setModel(param: CarouselItemModel) {
        this.model = param

        this.model?.let {
            view()?.setTitle(param.title!!)
            view()?.setPoster(param.path)
            view()?.setOverview(param.overview!!)
            view()?.setRanking(param.ranking!!)

            if (param.hasVideo)
                client.videos(param.type!!, param.id, this)
        }
    }


    override fun onSuccess(data: ListModel<VideoResultModel>) {
        view()?.setVideo(data.results!!.first())
    }

    override fun onFail(errorModel: ErrorModel) {
        view()?.showMessage(errorModel.code.toString() + " - " + errorModel.message)
    }
}
