package com.hernanbosqued.movie_db_client.ui.list

import com.hernanbosqued.movie_db_client.domain.CarouselModel

sealed class ListState {
    data class Search(val movies: Boolean, val tv: Boolean) : ListState()
    data class Carousel(val model: CarouselModel, val onTop: Boolean) : ListState()
    data class Message(val message: String) : ListState()
}
