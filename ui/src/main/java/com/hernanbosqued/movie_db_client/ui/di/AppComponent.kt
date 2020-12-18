package com.hernanbosqued.movie_db_client.ui.di

import android.app.Application
import com.hernanbosqued.movie_db_client.ui.CarouselClient
import com.hernanbosqued.movie_db_client.ui.presenter.ListPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(carouselClient: CarouselClient)
    fun inject(listPresenter: ListPresenter)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}