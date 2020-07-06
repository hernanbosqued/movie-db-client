package com.hernanbosqued.movie_db_client.domain

class CarouselModel {

    var method: String = ""
    var query: String = ""
    var page: Int = 0
    var results: Int = 0
    var totalPages: Int = 0
    var totalResults: Int = 0

    var title: String = ""
        get() = query.let { field.format(it) }

    var list: MutableList<CarouselItemModel> = ArrayList()
}
