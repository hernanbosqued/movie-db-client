package com.hernanbosqued.movie_db_client.ui.di

import android.app.Application
import android.content.Context
import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.repo.RepositoryImpl
import com.hernanbosqued.movie_db_client.ui.ResourcesRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class AppModule {
    @Binds
    abstract fun provideContext(application: Application?): Context

    @Binds
    abstract fun provideResources(impl: ResourcesRepositoryImpl): ResourcesRepository

    companion object {
        @kotlin.jvm.JvmStatic
        @Provides
        fun provideApi(application: Application): Repository {
            return RepositoryImpl(application.applicationContext)
        }
    }
}