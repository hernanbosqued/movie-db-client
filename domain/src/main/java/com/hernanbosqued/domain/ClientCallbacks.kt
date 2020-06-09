package com.hernanbosqued.domain

interface ClientCallbacks<T> {
    fun onOK(response: T)
    fun onError(error: String)
}