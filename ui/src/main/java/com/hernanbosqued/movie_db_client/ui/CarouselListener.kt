package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.model.ResultModel

interface ScrollListener {
    fun onLastItemReached()
}

interface ItemListener{
    fun onItemClicked(model: ResultModel)
}

interface CarouselViewListeners {
    fun onCarouselClicked(model: CarouselModel)
}

interface CarouselListeners: ItemListener, CarouselViewListeners