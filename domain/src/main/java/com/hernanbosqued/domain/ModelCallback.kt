package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ErrorModel

interface ModelCallback<T> {
    fun onSuccess(data: T)
    fun onFail(errorModel: ErrorModel)
}