package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.CarouselService
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselFragment
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselContract
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CarouselFragmentModule {

    @Binds
    abstract fun provideCarouselView(activity: CarouselFragment): CarouselContract.View

    companion object {
        @Provides
        fun provideCarouselPresenter(view: CarouselContract.View, service: CarouselService, resources: ResourcesRepository): CarouselContract.Presenter {
            return CarouselPresenter(view, service, resources)
        }
    }
}