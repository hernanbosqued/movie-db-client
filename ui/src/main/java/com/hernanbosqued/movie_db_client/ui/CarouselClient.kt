package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.CarouselClientCallbacks
import com.hernanbosqued.movie_db_client.domain.ListModel
import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.RepositoryCallbacks
import com.hernanbosqued.movie_db_client.domain.RepositoryCallbacksImpl
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import com.hernanbosqued.movie_db_client.domain.VideoResultModel
import javax.inject.Inject

class CarouselClient {

    init {
        MyApp.component.poke(this)
    }

    @Inject
    lateinit var repository: Repository
    @Inject
    lateinit var resources: ResourcesRepository

    fun searchBoth(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.searchBoth(
            page,
            query,
            RepositoryCallbacksImpl(
                resources.searchBoth(),
                callbacks
            )
        )
    }

    fun searchTV(page: Int, query: String, callbacks: CarouselClientCallbacks) {
        repository.searchTV(
            page, query,
            RepositoryCallbacksImpl(
                resources.searchTV(),
                callbacks
            )
        )
    }

    fun searchMovies(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.searchMovies(
            page,
            query,
            RepositoryCallbacksImpl(
                resources.searchMovies(),
                callbacks
            )
        )
    }

    fun moviesPopular(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.moviesPopular(
            page,
            RepositoryCallbacksImpl(
                resources.moviesPopular(),
                callbacks
            )
        )
    }

    fun moviesTopRated(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.moviesTopRated(
            page,
            RepositoryCallbacksImpl(
                resources.moviesTopRated(),
                callbacks
            )
        )
    }

    fun tvPopular(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.tvPopular(
            page,
            RepositoryCallbacksImpl(
                resources.tvPopular(),
                callbacks
            )
        )
    }

    fun tvTopRated(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.tvTopRated(
            page,
            RepositoryCallbacksImpl(
                resources.tvTopRated(),
                callbacks
            )
        )
    }

    fun videos(type: String, id: Int, callbacks: RepositoryCallbacks<ListModel<VideoResultModel>>) {
        repository.videos(type, id, callbacks)
    }
}


