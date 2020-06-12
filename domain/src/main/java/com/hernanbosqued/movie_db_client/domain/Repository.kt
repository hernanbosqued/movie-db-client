package com.hernanbosqued.movie_db_client.domain

import com.hernanbosqued.movie_db_client.domain.model.ListModel
import com.hernanbosqued.movie_db_client.domain.model.VideoModel

interface Repository {
    fun getMoviesPopular(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getMoviesTopRated(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getTVPopular(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getTVTopRated(page: Int, callbacks: ModelCallbacks<ListModel>)

    fun searchTVShows(page: Int, query: String?, callbacks: ModelCallbacks<ListModel>)
    fun searchMovies(page: Int, query: String?, callbacks: ModelCallbacks<ListModel>)
    fun searchBoth(page: Int, query: String?, callbacks: ModelCallbacks<ListModel>)

    fun getVideos(type: String, id: Int, callbacks: ModelCallbacks<VideoModel>)
}