package com.hernanbosqued.movie_db_client.domain

interface Repository {
    fun carouselList(callback: RepositoryCallback<CarouselListModel>)

    fun moviesPopular(page: Int, callback: RepositoryCallback<ListModel<MovieResultModel>>)
    fun moviesTopRated(page: Int, callback: RepositoryCallback<ListModel<MovieResultModel>>)
    fun tvPopular(page: Int, callback: RepositoryCallback<ListModel<TVResultModel>>)
    fun tvTopRated(page: Int, callback: RepositoryCallback<ListModel<TVResultModel>>)

    fun searchTV(page: Int, query: String?, callback: RepositoryCallback<ListModel<TVResultModel>>)
    fun searchMovies(page: Int, query: String?, callback: RepositoryCallback<ListModel<MovieResultModel>>)
    fun searchBoth(page: Int, query: String?, callback: RepositoryCallback<ListModel<ResultModel>>)

    fun tvDetails(id: Int, callback: RepositoryCallback<TVDetailsModel>)
    fun tvSeasonDetails(id: Int, seasonNumber: Int, callback: RepositoryCallback<SeasonModel>)

    fun videos(type: String, id: Int, callback: RepositoryCallback<ListModel<VideoResultModel>>)
}