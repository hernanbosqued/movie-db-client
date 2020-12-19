package com.hernanbosqued.movie_db_client.ui.contract

import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel

class CarouselContract {
    interface View {
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
        fun showEmpty(error:String)
        fun hideEmpty()
        fun showCarouselData(model: CarouselModel)
        fun setInfo(title: String, page: Int, totalPages: Int, totalResults: Int, results: Int)
        fun addData(data: MutableList<CarouselItemModel>)
        fun hideInfo()
        fun showInfo()
    }

    interface Presenter {
        fun load(first: Boolean)
        fun onCarouselClicked()
    }
}