package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.list.ListContract
import com.hernanbosqued.movie_db_client.ui.list.ListFragment
import com.hernanbosqued.movie_db_client.ui.list.ListPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ListFragmentModule {

    @Binds
    abstract fun provideListView(activity: ListFragment): ListContract.View

    companion object {
        @Provides
        fun provideListPresenter(view: ListContract.View, repository: Repository, resources: ResourcesRepository): ListContract.Presenter {
            return ListPresenter(view, repository, resources)
        }
    }
}