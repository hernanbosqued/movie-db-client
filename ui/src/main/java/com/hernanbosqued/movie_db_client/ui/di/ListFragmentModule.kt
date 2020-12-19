package com.hernanbosqued.movie_db_client.ui.di

import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.ui.ListActivity
import com.hernanbosqued.movie_db_client.ui.contract.ListContract
import com.hernanbosqued.movie_db_client.ui.fragment.ListFragment
import com.hernanbosqued.movie_db_client.ui.presenter.ListPresenter
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ListFragmentModule {
    @Binds
    abstract fun provideView(activity: ListFragment): ListContract.View

    companion object {
        @Provides
        fun providePresenter(view: ListContract.View, repository: Repository, resources: ResourcesRepository): ListContract.Presenter {
            return ListPresenter(view, repository, resources)
        }
    }
}