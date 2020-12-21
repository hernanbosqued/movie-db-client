package com.hernanbosqued.movie_db_client.ui

import android.app.Application
import com.hernanbosqued.movie_db_client.ui.di.ComponentHolder
import com.hernanbosqued.movie_db_client.ui.di.DaggerAppComponent

class MyApp : Application() {

    override fun onCreate() {
        ComponentHolder.components += DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }
}