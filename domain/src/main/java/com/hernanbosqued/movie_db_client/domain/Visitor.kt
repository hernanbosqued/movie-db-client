package com.hernanbosqued.movie_db_client.domain

import com.hernanbosqued.movie_db_client.domain.model.MovieResultModel
import com.hernanbosqued.movie_db_client.domain.model.PersonResultModel
import com.hernanbosqued.movie_db_client.domain.model.ResultModel
import com.hernanbosqued.movie_db_client.domain.model.TVResultModel

interface Visitor {
    fun accept(visitable: TVResultModel)
    fun accept(visitable: MovieResultModel)
    fun accept(visitable: PersonResultModel)
    fun accept(visitable: ResultModel)
}