package com.hernanbosqued.movie_db_client.domain

interface Repository {
    fun carouselList(callbacks: RepositoryCallbacks<CarouselListModel>)

    fun moviesPopular(page: Int, callbacks: RepositoryCallbacks<BaseListModel<MovieResultModel>>)
    fun moviesTopRated(page: Int, callbacks: RepositoryCallbacks<BaseListModel<MovieResultModel>>)
    fun tvPopular(page: Int, callbacks: RepositoryCallbacks<BaseListModel<TVResultModel>>)
    fun tvTopRated(page: Int, callbacks: RepositoryCallbacks<BaseListModel<TVResultModel>>)

    fun searchTV(page: Int, query: String?, callbacks: RepositoryCallbacks<BaseListModel<TVResultModel>>)
    fun searchMovies(page: Int, query: String?, callbacks: RepositoryCallbacks<BaseListModel<MovieResultModel>>)
    fun searchBoth(page: Int, query: String?, callbacks: RepositoryCallbacks<BaseListModel<ResultModel>>)

    fun videos(type: String, id: Int, callbacks: RepositoryCallbacks<BaseListModel<VideoResultModel>>)
}