package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.repo.RepositoryImpl
import com.hernanbosqued.movie_db_client.ui.ResourcesRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class AppModule {
    @Binds
    abstract fun provideResources(impl: ResourcesRepositoryImpl): ResourcesRepository

    @Binds
    abstract fun provideApi(impl: RepositoryImpl): Repository
}