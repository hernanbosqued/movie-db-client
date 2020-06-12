package com.hernanbosqued.movie_db_client.domain

interface ClientCallbacks<T> {
    fun onOK(response: T)
    fun onError(error: String)
}