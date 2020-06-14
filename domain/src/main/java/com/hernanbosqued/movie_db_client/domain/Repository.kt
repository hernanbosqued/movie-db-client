package com.hernanbosqued.movie_db_client.domain

import com.hernanbosqued.movie_db_client.domain.model.ListModel
import com.hernanbosqued.movie_db_client.domain.model.MovieListModel
import com.hernanbosqued.movie_db_client.domain.model.TVListModel
import com.hernanbosqued.movie_db_client.domain.model.VideoModel

interface Repository {
    fun getMoviesPopular(page: Int, callbacks: ModelCallbacks<MovieListModel>)
    fun getMoviesTopRated(page: Int, callbacks: ModelCallbacks<MovieListModel>)
    fun searchMovies(page: Int, query: String?, callbacks: ModelCallbacks<MovieListModel>)

    fun getTVPopular(page: Int, callbacks: ModelCallbacks<TVListModel>)
    fun getTVTopRated(page: Int, callbacks: ModelCallbacks<TVListModel>)
    fun searchTVShows(page: Int, query: String?, callbacks: ModelCallbacks<TVListModel>)

    fun searchBoth(page: Int, query: String?, callbacks: ModelCallbacks<ListModel>)

    fun getVideos(type: String, id: Int, callbacks: ModelCallbacks<VideoModel>)
}