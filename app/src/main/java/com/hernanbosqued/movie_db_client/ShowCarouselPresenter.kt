package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel

class ShowCarouselPresenter(private var client: (Int, ClientCallbacks<ListModel>) -> Unit) : BaseCarouselPresenter() {

    private var page = 1

    override fun load() {
        if (model.isEmpty()) {
            view()?.showProgress()
            client.invoke(page, this)
        }
    }

    override fun loadMore() {
        view()?.showProgress()
        client.invoke(page, this)
    }
}