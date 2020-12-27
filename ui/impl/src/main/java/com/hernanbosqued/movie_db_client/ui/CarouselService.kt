package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.*
import javax.inject.Inject

class CarouselService @Inject constructor(var repository: Repository, var resources: ResourcesRepository) {

    fun searchBoth(page: Int, query: String, callback: CarouselClientCallback) {
        repository.searchBoth(page, query, RepositoryCallbackImpl(resources.searchBoth(), query, callback))
    }

    fun searchTV(page: Int, query: String, callback: CarouselClientCallback) {
        repository.searchTV(page, query, RepositoryCallbackImpl(resources.searchTV(), query, callback))
    }

    fun searchMovies(page: Int, query: String, callback: CarouselClientCallback) {
        repository.searchMovies(page, query, RepositoryCallbackImpl(resources.searchMovies(), query, callback))
    }

    fun moviesPopular(page: Int, query: String, callback: CarouselClientCallback) {
        repository.moviesPopular(page, RepositoryCallbackImpl(resources.moviesPopular(), query, callback))
    }

    fun moviesTopRated(page: Int, query: String, callback: CarouselClientCallback) {
        repository.moviesTopRated(page, RepositoryCallbackImpl(resources.moviesTopRated(), query, callback))
    }

    fun tvPopular(page: Int, query: String, callback: CarouselClientCallback) {
        repository.tvPopular(page, RepositoryCallbackImpl(resources.tvPopular(), query, callback))
    }

    fun tvDetails(id: Int, callback: RepositoryCallback<TVDetailsModel>) {
        repository.tvDetails(id, callback)
    }

    fun videos(type: String, id: Int, callback: RepositoryCallback<ListModel<VideoResultModel>>) {
        repository.videos(type, id, callback)
    }
}


