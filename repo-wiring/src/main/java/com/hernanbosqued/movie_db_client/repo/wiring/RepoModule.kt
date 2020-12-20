package com.hernanbosqued.movie_db_client.repo.wiring

import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.repo.RepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModule {
    @Binds
    abstract fun provideApi(impl: RepositoryImpl): Repository
}