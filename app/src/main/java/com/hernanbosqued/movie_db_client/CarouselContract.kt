package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.model.ResultModel

class CarouselContract {
    interface View {
        fun showItems(model: List<ResultModel>)
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
        fun showEmpty()
        fun hideEmpty()
    }

    interface Presenter {
        fun load()
        fun loadMore()
    }
}