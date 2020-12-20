package com.hernanbosqued.movie_db_client.ui.di

import androidx.lifecycle.ViewModel
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.ResourcesRepositoryImpl
import com.hernanbosqued.movie_db_client.ui.detail.DetailViewModel
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
    abstract fun bindMyViewModel(myViewModel: DetailViewModel): ViewModel

}