package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.ModelCallbacks
import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.model.ErrorModel
import com.hernanbosqued.movie_db_client.domain.model.MovieListModel

class CarouselClient(private val repository: Repository) {

    fun getPopularMoviesList() {
        repository.getMoviesPopular(1, object : ModelCallbacks<MovieListModel> {
            override fun onSuccess(data: MovieListModel) {
            }

            override fun onFail(errorModel: ErrorModel) {
            }
        })
    }
}