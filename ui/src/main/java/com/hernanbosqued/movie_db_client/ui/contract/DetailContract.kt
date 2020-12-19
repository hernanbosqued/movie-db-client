package com.hernanbosqued.movie_db_client.ui.contract

import com.hernanbosqued.movie_db_client.domain.VideoResultModel

class DetailContract {
    interface View {
        fun setTitle(name: String)
        fun setOverview(overview: String)
        fun setPoster(posterPath: String?)
        fun setRanking(ranking: String)
        fun showMessage(message: String)
        fun setVideo(data: VideoResultModel)
    }

    interface Presenter {
        fun start()
    }
}
