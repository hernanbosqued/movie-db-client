package com.hernanbosqued.movie_db_client.ui

import android.app.Application
import android.content.Context
import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.repo.RepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class MyModule(val app: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return app
    }

    @Provides
    fun provide(): ResourcesRepository {
        return ResourcesRepositoryImpl(app)
    }

    @Provides
    fun provideApi(): Repository {
        return RepositoryImpl(app)
    }
}