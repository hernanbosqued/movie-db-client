package com.hernanbosqued.movie_db_client.domain

interface CarouselClientCallback {
    fun onOK(model: CarouselModel)
    fun onError(error: String)
}
