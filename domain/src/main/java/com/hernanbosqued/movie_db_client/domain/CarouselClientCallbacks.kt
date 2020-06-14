package com.hernanbosqued.movie_db_client.domain

import com.hernanbosqued.movie_db_client.domain.CarouselItemModel

interface CarouselClientCallbacks {
    fun onOK(data: CarouselItemModel)
    fun onError(error: String)
}
