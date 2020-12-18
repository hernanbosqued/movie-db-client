package com.hernanbosqued.movie_db_client.ui.contract

import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.domain.MEDIATYPE

class ListContract {
    interface View {
        fun showMessage(message: String)
        fun addCarousel(model: CarouselModel, onTop: Boolean)
        fun scrollTop()
        fun initialSelection(moviesChecked: Boolean, tvChecked: Boolean)
    }

    interface Presenter {
        fun processQuery(query: String)
        fun checkboxChanged(type: MEDIATYPE)
    }
}