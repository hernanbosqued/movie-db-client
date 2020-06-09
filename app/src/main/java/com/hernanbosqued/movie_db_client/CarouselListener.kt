package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.domain.model.ResultModel

interface ScrollListener {
    fun onLastItemReached()
}

interface ItemListener{
    fun onItemClicked(model: ResultModel)
}

interface CarouselViewListeners {
    fun onCarouselClicked(model: ListModel)
}

interface CarouselListeners: ItemListener, CarouselViewListeners