package com.hernanbosqued.movie_db_client.domain

interface Visitable {
    fun visit(visitor: Visitor)
}