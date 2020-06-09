package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ErrorModel
import com.hernanbosqued.domain.model.ListModel

object Client {

    lateinit var repo: Repository

    fun getDetails(id: Int, callback: ClientCallbacks<ListModel>) {
        repo.getDetails(id, ClientCallbackImpl(callback))
    }

    fun getMoviesPopular(page: Int, query: String?, callback: ClientCallbacks<ListModel>) {
        repo.getMoviesPopular(page, ClientCallbackImpl(callback))
    }

    fun getMoviesTopRated(page: Int, query: String?, callback: ClientCallbacks<ListModel>) {
        repo.getMoviesTopRated(page, ClientCallbackImpl(callback))
    }

    fun getTVPopular(page: Int, query: String?, callback: ClientCallbacks<ListModel>) {
        repo.getTVPopular(page, ClientCallbackImpl(callback))
    }

    fun getTVTopRated(page: Int, query: String?, callback: ClientCallbacks<ListModel>) {
        repo.getTVTopRated(page, ClientCallbackImpl(callback))
    }

    fun searchTVShows(page: Int, query: String?, callback: ClientCallbacks<ListModel>) {
        repo.searchTVShows(page, query, ClientCallbackImpl(callback))
    }

    fun searchMovies(page: Int, query: String?, callback: ClientCallbacks<ListModel>) {
        repo.searchMovies(page, query, ClientCallbackImpl(callback))
    }

    fun searchBoth(page: Int, query: String?, callback: ClientCallbacks<ListModel>) {
        repo.searchBoth(page, query, ClientCallbackImpl(callback))
    }

    class ClientCallbackImpl<T>(public val callbacks: ClientCallbacks<T>) : ModelCallbacks<T> {

        override fun onSuccess(data: T) {
            callbacks.onOK(data)
        }

        override fun onFail(errorModel: ErrorModel) {
            callbacks.onError(errorModel.code.toString() + " - " + errorModel.message)
        }
    }
}