package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.model.ListModel

class CarouselContract {
    interface View {
        fun addData(model: ListModel)
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
        fun showEmpty()
        fun hideEmpty()
        fun showCarouselData(model: ListModel)
    }

    interface Presenter {
        fun load()
        fun onCarouselClicked()
    }
}