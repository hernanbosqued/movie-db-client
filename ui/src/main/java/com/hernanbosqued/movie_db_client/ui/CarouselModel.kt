package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.ClientCallbacks
import com.hernanbosqued.movie_db_client.domain.model.ListModel

class CarouselModel(
    val client: ( (Int, String?, ClientCallbacks<ListModel>) -> Unit),
    var title: String,
    val query: String?
) {
    var response: ListModel = ListModel()
}