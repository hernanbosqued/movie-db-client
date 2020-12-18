package com.hernanbosqued.movie_db_client.ui

import android.app.Application
import com.hernanbosqued.movie_db_client.ui.di.AppComponent
import com.hernanbosqued.movie_db_client.ui.di.DaggerAppComponent

class MyApp : Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().application(this).build()
    }
}