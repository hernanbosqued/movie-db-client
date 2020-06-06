package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel

class MainContract {
    interface View {
        fun showMessage(message: String)
        fun showProgress()
        fun hideProgress()
        fun addSearchCarousel(client: (Int, String, ClientCallbacks<ListModel>) -> Unit, title: String, query: String)
        fun addViewCarousel(client: (Int, ClientCallbacks<ListModel>) -> Unit, title: String)
    }

    interface Presenter {
        fun processQuery(query: String, includeMovies: Boolean, includeTVShows: Boolean)
        fun prepareLists()
    }
}