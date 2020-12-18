package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.CarouselClientCallback
import com.hernanbosqued.movie_db_client.domain.ListModel
import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.RepositoryCallback
import com.hernanbosqued.movie_db_client.domain.RepositoryCallbackImpl
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.domain.VideoResultModel
import javax.inject.Inject

class CarouselClient {

    init {
        MyApp.appComponent.inject(this)
    }

    @Inject
    lateinit var repository: Repository

    @Inject
    lateinit var resources: ResourcesRepository

    fun searchBoth(page: Int, query: String, callback: CarouselClientCallback) {
        repository.searchBoth(
            page,
            query,
            RepositoryCallbackImpl(resources.searchBoth(), query, callback)
        )
    }

    fun searchTV(page: Int, query: String, callback: CarouselClientCallback) {
        repository.searchTV(
            page,
            query,
            RepositoryCallbackImpl(resources.searchTV(), query, callback)
        )
    }

    fun searchMovies(page: Int, query: String, callback: CarouselClientCallback) {
        repository.searchMovies(
            page,
            query,
            RepositoryCallbackImpl(resources.searchMovies(), query, callback)
        )
    }

    fun moviesPopular(page: Int, query: String, callback: CarouselClientCallback) {
        repository.moviesPopular(
            page,
            RepositoryCallbackImpl(resources.moviesPopular(), query, callback)
        )
    }

    fun moviesTopRated(page: Int, query: String, callback: CarouselClientCallback) {
        repository.moviesTopRated(
            page,
            RepositoryCallbackImpl(resources.moviesTopRated(), query, callback)
        )
    }

    fun tvPopular(page: Int, query: String, callback: CarouselClientCallback) {
        repository.tvPopular(
            page,
            RepositoryCallbackImpl(resources.tvPopular(), query, callback)
        )
    }

    fun tvTopRated(page: Int, query: String, callback: CarouselClientCallback) {
        repository.tvTopRated(
            page,
            RepositoryCallbackImpl(resources.tvTopRated(), query, callback)
        )
    }

    fun videos(type: String, id: Int, callback: RepositoryCallback<ListModel<VideoResultModel>>) {
        repository.videos(type, id, callback)
    }
}


