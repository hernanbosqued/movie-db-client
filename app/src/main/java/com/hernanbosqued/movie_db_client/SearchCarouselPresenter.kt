package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel
import java.util.*

class SearchCarouselPresenter(private var client: (Int, String, ClientCallbacks<ListModel>) -> Unit, private val query: String) : BaseCarouselPresenter() {

    private var page = 1

    override fun load() {
        if (model.isEmpty()) {
            view()?.showProgress()
            client.invoke(page, query, this)
        }
    }

    override fun loadMore() {
        view()?.showProgress()
        client.invoke(page, query, this)
    }
}