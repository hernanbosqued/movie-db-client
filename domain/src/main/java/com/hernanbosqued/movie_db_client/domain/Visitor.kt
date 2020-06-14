package com.hernanbosqued.movie_db_client.domain

interface Visitor {
    fun accept(visitable: TVResultModel)
    fun accept(visitable: MovieResultModel)
    fun accept(visitable: PersonResultModel)
    fun accept(visitable: VideoResultModel)
    fun accept(visitable: ResultModel)
}