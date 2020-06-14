package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.*

class DetailPresenter(view: DetailContract.View, private val repository: Repository) : BasePresenter<ResultModel, DetailContract.View>(view), DetailContract.Presenter, Visitor {

    override fun setModel(model: ResultModel) {
        this.model = model
        model.visit(this)
    }

    override fun accept(visitable: ResultModel) {
        view()?.setOverview(visitable.overview)
        view()?.setRanking(visitable.ranking)
        if (visitable.hasVideo) {
               // repository.getVideos(ResultModel.MEDIATYPE.value, visitable.id, this)
        }
    }

    override fun accept(visitable: TVResultModel) {
        visitable.name?.let { name -> view()?.setTitle(name) }
        visitable.posterPath?.let { path -> view()?.setPoster(path) }
    }

    override fun accept(visitable: MovieResultModel) {
        visitable.title?.let { title -> view()?.setTitle(title) }
        visitable.posterPath?.let { path -> view()?.setPoster(path) }
    }

    override fun accept(visitable: PersonResultModel) {
        visitable.name?.let { name -> view()?.setTitle(name) }
        visitable.profilePath?.let { path -> view()?.setPoster(path) }
        view()?.setRanking(visitable.ranking)
    }

    override fun accept(visitable: VideoResultModel) {
    }


//    override fun onSuccess(data: VideoModel) {
//        view()?.setVideo(data)
//    }
//
//    override fun onFail(errorModel: ErrorModel) {
//        view()?.showMessage(errorModel.code.toString() + " - " + errorModel.message)
//    }
}
