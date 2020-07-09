package com.hernanbosqued.movie_db_client.domain

interface RepositoryCallback<T>{
    fun onSuccess(data:T)
    fun onFail(error:ErrorModel)
}