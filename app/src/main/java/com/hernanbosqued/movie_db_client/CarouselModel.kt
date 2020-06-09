package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel

class CarouselModel(val client: ((Int, String?, ClientCallbacks<ListModel>) -> Unit)?, var title: String, val query: String?) {
    var response: ListModel = ListModel()
}