package com.hernanbosqued.movie_db_client.domain

class CarouselClient(
    private val repository: Repository,
    private val resources: ResourcesRepository
) {

    fun searchBoth(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.searchBoth(
            page,
            query,
            RepositoryCallbacksImpl(resources.searchBoth(), callbacks)
        )
    }

    fun searchTV(page: Int, query: String, callbacks: CarouselClientCallbacks) {
        repository.searchTV(page, query, RepositoryCallbacksImpl(resources.searchTV(), callbacks))
    }

    fun searchMovies(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.searchMovies(
            page,
            query,
            RepositoryCallbacksImpl(resources.searchMovies(), callbacks)
        )
    }

    fun moviesPopular(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.moviesPopular(
            page,
            RepositoryCallbacksImpl(resources.moviesPopular(), callbacks)
        )
    }

    fun moviesTopRated(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.moviesTopRated(
            page,
            RepositoryCallbacksImpl(resources.moviesTopRated(), callbacks)
        )
    }

    fun tvPopular(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.tvPopular(page, RepositoryCallbacksImpl(resources.tvPopular(), callbacks))
    }

    fun tvTopRated(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.tvTopRated(page, RepositoryCallbacksImpl(resources.tvTopRated(), callbacks))
    }

    fun videos(type: String, id: Int, callbacks: RepositoryCallbacks<ListModel<VideoResultModel>>) {
        repository.videos(type, id, callbacks)
    }
}


