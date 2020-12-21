package com.hernanbosqued.movie_db_client.ui.di

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.ResourcesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(impl: Application): Context

    @Binds
    abstract fun provideResources(impl: ResourcesRepositoryImpl): ResourcesRepository

    @Binds
    abstract fun provideViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(DetailViewModel::class)
//    abstract fun detailViewModel(impl: DetailViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(ListViewModel::class)
//    abstract fun listViewModel(impl: ListViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(CarouselViewModel::class)
//    abstract fun carouselViewModel(impl: CarouselViewModel): ViewModel
}