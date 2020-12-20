package com.hernanbosqued.movie_db_client.ui.di

import androidx.lifecycle.ViewModelProvider
import com.hernanbosqued.movie_db_client.ui.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}