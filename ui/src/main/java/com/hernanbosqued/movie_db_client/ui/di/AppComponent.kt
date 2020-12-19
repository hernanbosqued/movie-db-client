package com.hernanbosqued.movie_db_client.ui.di

import android.app.Application
import com.hernanbosqued.movie_db_client.ui.CarouselClient
import com.hernanbosqued.movie_db_client.ui.ListActivity
import com.hernanbosqued.movie_db_client.ui.fragment.ListFragment
import com.hernanbosqued.movie_db_client.ui.presenter.ListPresenter
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(application: DaggerApplication)

    fun inject(client: CarouselClient)

    fun carouselClient(): CarouselClient

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}