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
}