package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.CarouselClient
import com.hernanbosqued.movie_db_client.ui.CarouselFragment
import com.hernanbosqued.movie_db_client.ui.contract.CarouselContract
import com.hernanbosqued.movie_db_client.ui.presenter.CarouselPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CarouselFragmentModule {

    @Binds
    abstract fun provideCarouselView(activity: CarouselFragment): CarouselContract.View

    companion object {
        @Provides
        fun provideCarouselPresenter(view: CarouselContract.View, client: CarouselClient, resources: ResourcesRepository): CarouselContract.Presenter {
            return CarouselPresenter(view, client, resources)
        }
    }
}