package com.hernanbosqued.movie_db_client.ui.di

import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.ResourcesRepositoryImpl
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselViewModel
import com.hernanbosqued.movie_db_client.ui.detail.DetailViewModel
import com.hernanbosqued.movie_db_client.ui.list.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AppModule {
    @Binds
    abstract fun provideResources(impl: ResourcesRepositoryImpl): ResourcesRepository

    @Binds
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    abstract fun detailViewModel(impl: DetailViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun listViewModel(impl: ListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CarouselViewModel::class)
    abstract fun carouselViewModel(impl: CarouselViewModel): ViewModel
}