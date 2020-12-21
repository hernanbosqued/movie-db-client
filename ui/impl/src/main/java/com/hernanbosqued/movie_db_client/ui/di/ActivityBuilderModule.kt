package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.ui.carousel.CarouselFragment
import com.hernanbosqued.movie_db_client.ui.detail.DetailFragment
import com.hernanbosqued.movie_db_client.ui.list.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun listFragment(): ListFragment

    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun detailFragment(): DetailFragment

    @ContributesAndroidInjector(modules = [AppModule::class])
    abstract fun carouselFragment(): CarouselFragment
}