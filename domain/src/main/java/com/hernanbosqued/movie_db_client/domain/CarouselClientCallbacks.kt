package com.hernanbosqued.movie_db_client.domain

interface CarouselClientCallbacks {
    fun onOK(model: CarouselModel)
    fun onError(error: String)
}
