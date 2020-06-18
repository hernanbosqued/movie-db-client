package com.hernanbosqued.movie_db_client.domain

class CarouselModel(val method: String, title: String, val query: String?) {

    //var endpoint: (String?, CarouselClientCallbacks) -> Unit

    var title = query?.let { title.format(it) } ?: title
    var list: MutableList<CarouselItemModel> = ArrayList()
}
