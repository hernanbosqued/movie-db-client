package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ErrorModel

interface ModelCallbacks<T> {
    fun onSuccess(data: T)
    fun onFail(errorModel: ErrorModel)
}