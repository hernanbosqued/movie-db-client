package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.ui.ListActivity
import com.hernanbosqued.movie_db_client.ui.fragment.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = [ListFragmentModule::class])
    abstract fun listActivity(): ListFragment
}