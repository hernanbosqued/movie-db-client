package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.ResourcesRepository
import dagger.Provides
import javax.inject.Inject

class ResourcesRepositoryImpl2: ResourcesRepository {

    override fun moviesPopular(): String {
        return "ATLANTA1"
    }

    override fun moviesTopRated(): String {
        return "ATLANTA1"
    }

    override fun tvPopular(): String {
        return "ATLANTA1"
    }

    override fun tvTopRated(): String {
        return "ATLANTA1"
    }

    override fun searchBoth(): String {
        return "ATLANTA1"
    }

    override fun searchMovies(): String {
        return "ATLANTA1"
    }

    override fun searchTV(): String {
        return "ATLANTA1"
    }

    override fun getD(): String {
        return "ATLANTA1"
    }

    override fun getAccent(): Int {
        return 0XFF00FF
    }
}
