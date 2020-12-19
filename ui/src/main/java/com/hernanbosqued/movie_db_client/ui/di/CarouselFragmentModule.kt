package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.ui.CarouselClient
import com.hernanbosqued.movie_db_client.ui.CarouselFragment
import com.hernanbosqued.movie_db_client.ui.contract.CarouselContract
import com.hernanbosqued.movie_db_client.ui.contract.DetailContract
import com.hernanbosqued.movie_db_client.ui.fragment.DetailFragment
import com.hernanbosqued.movie_db_client.ui.presenter.CarouselPresenter
import com.hernanbosqued.movie_db_client.ui.presenter.DetailPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CarouselFragmentModule {

    @Binds
    abstract fun provideCarouselView(activity: CarouselFragment): CarouselContract.View

    companion object {
        @Provides
        fun provideCarouselPresenter(view: CarouselContract.View, client: CarouselClient): CarouselContract.Presenter {
            return CarouselPresenter(view, client)
        }
    }
}