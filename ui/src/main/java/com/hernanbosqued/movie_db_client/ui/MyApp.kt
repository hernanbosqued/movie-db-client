package com.hernanbosqued.movie_db_client.ui

import android.app.Application

class MyApp : Application() {
    companion object{
        lateinit var component: MyComponent
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerMyComponent.builder().myModule(MyModule(this)).build()
    }
}