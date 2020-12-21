package com.hernanbosqued.movie_db_client.ui.di

import android.app.Application
import com.hernanbosqued.movie_db_client.repo.wiring.RepoModule
import com.hernanbosqued.movie_db_client.ui.carousel.CarouselViewModel
import com.hernanbosqued.movie_db_client.ui.detail.DetailViewModel
import com.hernanbosqued.movie_db_client.ui.list.ListFragment
import com.hernanbosqued.movie_db_client.ui.list.ListViewModel
import dagger.BindsInstance
import dagger.Component

import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RepoModule::class,
        AppModule::class]
)
interface AppComponent {

    fun inject(viewModel: ListFragment)
    fun inject(viewModel: CarouselViewModel)
    fun inject(viewModel: DetailViewModel)
    fun inject(viewModel: ListViewModel)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}