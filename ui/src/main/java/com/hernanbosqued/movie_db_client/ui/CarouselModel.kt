package com.hernanbosqued.movie_db_client.ui

class CarouselModel(val endpoint: () -> Unit, title: String, val query: String?) {

    var title = query?.let { title.format(it) } ?: title

}
