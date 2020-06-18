package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel

class CarouselContract {
    interface View {
        fun addData(data: CarouselItemModel)
        fun addData(data: List<CarouselItemModel>)
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