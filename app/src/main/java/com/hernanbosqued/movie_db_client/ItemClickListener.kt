package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.model.ResultModel

interface ItemClickListener {
    fun onItemClick(model: ResultModel)
}

