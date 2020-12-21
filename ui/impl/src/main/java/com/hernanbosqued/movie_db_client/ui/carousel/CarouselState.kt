package com.hernanbosqued.movie_db_client.ui.carousel

import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel
import com.hernanbosqued.movie_db_client.domain.VideoResultModel

sealed class CarouselState {
    data class Data(val data: MutableList<CarouselItemModel>) : CarouselState()
    data class Info(val title: String, val page: Int, val totalPages: Int, val totalResults: Int, val results: Int) : CarouselState()
    data class Message(val data: String) : CarouselState()
}
