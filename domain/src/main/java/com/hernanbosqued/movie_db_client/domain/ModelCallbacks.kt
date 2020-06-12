package com.hernanbosqued.movie_db_client.domain

import com.hernanbosqued.movie_db_client.domain.model.ErrorModel

interface ModelCallbacks<T> {
    fun onSuccess(data: T)
    fun onFail(errorModel: ErrorModel)
}