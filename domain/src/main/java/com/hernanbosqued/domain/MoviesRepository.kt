package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ListModel

interface MoviesRepository {
    fun getList(page: Int, callback: ModelCallback<ListModel>)
    fun getMoviesPopular(page: Int, callback: ModelCallback<ListModel>)
}