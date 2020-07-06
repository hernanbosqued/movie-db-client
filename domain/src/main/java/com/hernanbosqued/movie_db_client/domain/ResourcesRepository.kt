package com.hernanbosqued.movie_db_client.domain

interface ResourcesRepository {
    fun searchBoth():String
    fun searchMovies():String
    fun searchTV():String
    fun getD():String
    fun getAccent():Int
    fun moviesPopular(): String
    fun moviesTopRated(): String
    fun tvPopular(): String
    fun tvTopRated(): String
    fun nothingSelected(): String
}