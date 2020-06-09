package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ListModel

interface Repository {
    fun getDetails(id: Int, callbacks: Client.ClientCallbackImpl<ListModel>)
    fun getMoviesPopular(page: Int, callbacks: Client.ClientCallbackImpl<ListModel>)
    fun getMoviesTopRated(page: Int, callbacks: Client.ClientCallbackImpl<ListModel>)
    fun getTVPopular(page: Int, callbacks: Client.ClientCallbackImpl<ListModel>)
    fun getTVTopRated(page: Int, callbacks: Client.ClientCallbackImpl<ListModel>)

    fun searchTVShows(page: Int, query: String?, callbacks: Client.ClientCallbackImpl<ListModel>)
    fun searchMovies(page: Int, query: String?, callbacks: Client.ClientCallbackImpl<ListModel>)
    fun searchBoth(page: Int, query: String?, callbacks: Client.ClientCallbackImpl<ListModel>)
}