package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ListModel

interface Repository {
    fun getList(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getMoviesPopular(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getMoviesTopRated(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getTVPopular(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getTVTopRated(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun searchTVShows(page: Int, query: String, modelCallbacksImpl: Client.ModelCallbacksImpl<ListModel>)
    fun searchMovies(page: Int, query: String, modelCallbacksImpl: Client.ModelCallbacksImpl<ListModel>)
    fun searchBoth(page: Int, query: String, modelCallbacksImpl: Client.ModelCallbacksImpl<ListModel>)
}