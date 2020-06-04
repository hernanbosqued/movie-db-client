package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ListModel

interface Repository {
    fun getList(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getMoviesPopular(page: Int, callbacks: ModelCallbacks<ListModel>)
    fun getTVPopular(page: Int, callbacks: ModelCallbacks<ListModel>)
}