package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.ui.CarouselService
import com.hernanbosqued.movie_db_client.ui.contract.DetailContract
import com.hernanbosqued.movie_db_client.ui.fragment.DetailFragment
import com.hernanbosqued.movie_db_client.ui.presenter.DetailPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class DetailFragmentModule {

    @Binds
    abstract fun provideDetailView(activity: DetailFragment): DetailContract.View

    companion object {
        @Provides
        fun provideDetailPresenter(view: DetailContract.View, service: CarouselService): DetailContract.Presenter {
            return DetailPresenter(view, service)
        }
    }
}