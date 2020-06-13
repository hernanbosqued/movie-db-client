package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.ModelCallbacks
import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.Visitor
import com.hernanbosqued.movie_db_client.domain.model.*

class DetailPresenter(view: DetailContract.View, private val repository: Repository) : BasePresenter<ResultModel, DetailContract.View>(view), DetailContract.Presenter, ModelCallbacks<VideoModel>, Visitor {

    override fun setModel(model: ResultModel) {
        this.model = model
        model.visit(this)
    }

    override fun accept(visitable: ResultModel) {
        view()?.setOverview(visitable.overview)
        view()?.setRanking(visitable.ranking)
        if (visitable.hasVideo) {
            repository.getVideos(visitable.getMediaType().value, visitable.id, this)
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

    override fun onSuccess(data: VideoModel) {
        view()?.setVideo(data)
    }

    override fun onFail(errorModel: ErrorModel) {
        view()?.showMessage(errorModel.code.toString() + " - " + errorModel.message)
    }
}
