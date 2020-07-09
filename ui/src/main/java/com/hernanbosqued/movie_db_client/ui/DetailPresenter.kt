package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.*

class DetailPresenter(
    view: DetailContract.View,
    model: CarouselItemModel,
    private val client: CarouselClient
) : BasePresenter<CarouselItemModel, DetailContract.View>(model, view),
    DetailContract.Presenter, RepositoryCallback<ListModel<VideoResultModel>> {

   init {
       view().setTitle(model().title!!)
       view().setPoster(model().path)
       view().setOverview(model().overview!!)
       view().setRanking(model().ranking!!)

       if (model().hasVideo) {
           client.videos(model().type!!, model().id, this)
       }
   }


    override fun onSuccess(data: ListModel<VideoResultModel>) {
        view().setVideo(data.results!!.first())
    }

    override fun onFail(errorModel: ErrorModel) {
        view().showMessage(errorModel.code.toString() + " - " + errorModel.message)
    }
}
