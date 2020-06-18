package com.hernanbosqued.movie_db_client.domain

interface CarouselClientCallbacks {
    fun onOK(data: List<CarouselItemModel>)
    fun onError(error: String)
}
