package com.hernanbosqued.movie_db_client.base.di

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class ContextModule {
    @Binds
    abstract fun provideContext(impl: Application): Context
}