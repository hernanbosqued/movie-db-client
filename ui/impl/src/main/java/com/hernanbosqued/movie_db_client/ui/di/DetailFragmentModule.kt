package com.hernanbosqued.movie_db_client.ui.di

import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.ui.CarouselService
import com.hernanbosqued.movie_db_client.ui.detail.DetailContract
import com.hernanbosqued.movie_db_client.ui.detail.DetailFragment
import com.hernanbosqued.movie_db_client.ui.detail.DetailViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DetailFragmentModule {

    @Binds
    abstract fun provideDetailView(activity: DetailFragment): DetailContract.View

    companion object {
        @Provides
        fun provideDetailPresenter(view: DetailContract.View, service: CarouselService): ViewModel {
            return DetailViewModel(service)
        }
    }
}