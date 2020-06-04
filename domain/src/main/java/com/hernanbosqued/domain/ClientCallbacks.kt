package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ListModel

interface ClientCallbacks {
    fun onOK(model: ListModel)
    fun onError(error: String)
}