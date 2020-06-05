package com.hernanbosqued.domain

interface ClientCallbacks<T> {
    fun onOK(model: T)
    fun onError(error: String)
}