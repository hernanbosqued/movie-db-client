package com.hernanbosqued.movie_db_client.domain

interface Repository {
    fun carouselList(callbacks: RepositoryCallbacks<CarouselListModel>)

    fun moviesPopular(page: Int, callbacks: RepositoryCallbacks<ListModel<MovieResultModel>>)
    fun moviesTopRated(page: Int, callbacks: RepositoryCallbacks<ListModel<MovieResultModel>>)
    fun tvPopular(page: Int, callbacks: RepositoryCallbacks<ListModel<TVResultModel>>)
    fun tvTopRated(page: Int, callbacks: RepositoryCallbacks<ListModel<TVResultModel>>)

    fun searchTV(page: Int, query: String?, callbacks: RepositoryCallbacks<ListModel<TVResultModel>>)
    fun searchMovies(page: Int, query: String?, callbacks: RepositoryCallbacks<ListModel<MovieResultModel>>)
    fun searchBoth(page: Int, query: String?, callbacks: RepositoryCallbacks<ListModel<ResultModel>>)

    fun videos(type: String, id: Int, callbacks: RepositoryCallbacks<ListModel<VideoResultModel>>)
}