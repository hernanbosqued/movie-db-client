package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.domain.model.VideoModel

class DetailContract {
    interface View {
        fun setTitle(name: String)
        fun setOverview(overview: String)
        fun setPoster(posterPath: String?)
        fun setRanking(ranking  : String)
        fun showMessage(message: String)
        fun setVideo(data: VideoModel)
    }

    interface Presenter {
        fun setModel(resultModel: ResultModel)
    }
}
