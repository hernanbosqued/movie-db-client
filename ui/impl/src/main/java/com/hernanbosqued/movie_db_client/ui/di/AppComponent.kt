package com.hernanbosqued.movie_db_client.ui.di

import android.app.Application
import com.hernanbosqued.movie_db_client.base.di.ContextModule
import com.hernanbosqued.movie_db_client.repo.wiring.RepoModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ContextModule::class,
        RepoModule::class,
        AppModule::class,
        ActivityBuilderModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {

    override fun inject(application: DaggerApplication)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent

        @BindsInstance
        fun application(application: Application): Builder
    }
}