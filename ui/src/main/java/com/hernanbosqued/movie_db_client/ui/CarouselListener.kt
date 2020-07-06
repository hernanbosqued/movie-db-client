package com.hernanbosqued.movie_db_client.ui

import android.view.View
import com.hernanbosqued.movie_db_client.domain.CarouselItemModel
import com.hernanbosqued.movie_db_client.domain.CarouselModel

interface ScrollListener {
    fun onLastItemReached()
}

interface ItemListener{
    fun onItemClicked(
        view: View,
        model: CarouselItemModel
    )
}

interface CarouselViewListeners {
    fun onCarouselClicked(model: CarouselModel)
}

interface CarouselListeners: ItemListener, CarouselViewListeners