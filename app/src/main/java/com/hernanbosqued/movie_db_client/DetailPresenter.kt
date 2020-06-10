package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ModelCallbacks
import com.hernanbosqued.domain.model.ErrorModel
import com.hernanbosqued.domain.model.ResultModel
import com.hernanbosqued.domain.model.TYPE
import com.hernanbosqued.domain.model.VideoModel
import com.hernanbosqued.repo.RepositoryImpl

class DetailPresenter(view: DetailContract.View) : BasePresenter<ResultModel, DetailContract.View>(view), DetailContract.Presenter, ModelCallbacks<VideoModel> {

    private var client = RepositoryImpl

    override fun setModel(resultModel: ResultModel) {
        this.model = resultModel
        fillViews()
    }

    private fun fillViews() {
        model?.let {
            view()?.setTitle(it.name)
            view()?.setOverview(it.overview)
            view()?.setPoster(it.posterPath)
            view()?.setRanking(it.voteAverage)
            if (it.hasVideo) {
                client.getVideos(TYPE.MOVIE.value, it.id, this )
            }
        }
    }

    override fun onSuccess(data: VideoModel) {
        view()?.setVideo(data)
    }

    override fun onFail(errorModel: ErrorModel) {
        view()?.showMessage(errorModel.code.toString() + " - " + errorModel.message)
    }
}
