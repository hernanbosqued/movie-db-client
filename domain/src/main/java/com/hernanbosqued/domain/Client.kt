package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ErrorModel
import com.hernanbosqued.domain.model.ListModel

object Client {

    lateinit var repo: Repository

    fun getMoviesPopular(page: Int, callback: ClientCallbacks<ListModel>) {
        repo.getMoviesPopular(page, ModelCallbacksImpl(callback))
    }

    fun getMoviesTopRated(page: Int, callback: ClientCallbacks<ListModel>) {
        repo.getMoviesTopRated(page, ModelCallbacksImpl(callback))
    }

    fun getTVPopular(page: Int, callback: ClientCallbacks<ListModel>) {
        repo.getTVPopular(page, ModelCallbacksImpl(callback))
    }

    fun getTVTopRated(page: Int, callback: ClientCallbacks<ListModel>) {
        repo.getTVTopRated(page, ModelCallbacksImpl(callback))
    }

    fun searchTVShows(page: Int, query:String, callback: ClientCallbacks<ListModel>) {
        repo.searchTVShows(page, query, ModelCallbacksImpl(callback))
    }
    fun searchMovies(page: Int, query:String,callback: ClientCallbacks<ListModel>) {
        repo.searchMovies(page, query, ModelCallbacksImpl(callback))
    }
    fun searchBoth(page: Int, query:String,callback: ClientCallbacks<ListModel>) {
        repo.searchBoth(page, query, ModelCallbacksImpl(callback))
    }

    class ModelCallbacksImpl<T>(private val callbacks: ClientCallbacks<T>) : ModelCallbacks<T>{

        override fun onSuccess(data: T) {
            callbacks.onOK(data)
        }

        override fun onFail(errorModel: ErrorModel) {
            callbacks.onError(errorModel.code.toString() + " - " + errorModel.message)
        }
    }
}