package com.hernanbosqued.movie_db_client.domain

class CarouselModel {

    var method: String = ""
    var query: String = ""
    val info: Boolean = false
    var page: Int = 0
    var results: Int = 0
    var totalPages: Int = 0
    var totalResults: Int = 0
    var title: String = ""
    var list: MutableList<CarouselItemModel> = ArrayList()
}
