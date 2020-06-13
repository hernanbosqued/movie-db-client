package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.model.ListModel
import com.hernanbosqued.movie_db_client.domain.model.ResultModel

class CarouselContract {
    interface View {
        fun addData(model: List<ResultModel>)
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
        fun showEmpty()
        fun showEmpty(error:String)
        fun hideEmpty()
        fun showCarouselData(model: CarouselModel)
        fun setTitle(title: String)
    }

    interface Presenter {
        fun load()
        fun onCarouselClicked()
    }
}