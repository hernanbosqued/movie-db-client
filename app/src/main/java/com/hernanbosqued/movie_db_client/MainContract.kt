package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.model.ResultModel

class MainContract {
    interface View {
        fun showItems(model: List<ResultModel>)
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
        fun showEmpty()
        fun hideEmpty()
        fun addCarousel(client: Client, title: String)
    }

    interface Presenter {
        fun processQuery(query: String)
        fun prepareLists()
    }
}