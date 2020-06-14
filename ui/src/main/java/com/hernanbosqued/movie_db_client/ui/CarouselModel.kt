package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.CarouselClientCallbacks
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel

class CarouselModel(val endpoint: (String?, CarouselClientCallbacks) -> Unit, title: String, val query: String?) {

    var title = query?.let { title.format(it) } ?: title
    var list: MutableList<CarouselItemModel> = ArrayList()
}
