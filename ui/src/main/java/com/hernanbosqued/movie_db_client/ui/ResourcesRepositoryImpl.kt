package com.hernanbosqued.movie_db_client.ui

import android.content.Context
import androidx.core.content.ContextCompat
import com.hernanbosqued.movie_db_client.domain.ResourcesRepository

class ResourcesRepositoryImpl(private val context: Context) :
    ResourcesRepository {

    override fun moviesPopular(): String {
        return context.getString(R.string.movies_popular)
    }

    override fun moviesTopRated(): String {
        return context.getString(R.string.movies_top_rated)
    }

    override fun tvPopular(): String {
        return context.getString(R.string.tv_popular)
    }

    override fun tvTopRated(): String {
        return context.getString(R.string.tv_top_rated)
    }

    override fun nothingSelected(): String {
        return context.getString(R.string.nothing_selected)
    }

    override fun searchBoth(): String {
        return context.getString(R.string.search_both)
    }

    override fun searchMovies(): String {
        return context.getString(R.string.search_movies)
    }

    override fun searchTV(): String {
        return context.getString(R.string.search_tv)
    }

    override fun getD(): String {
        return context.getString(R.string.ranking)
    }

    override fun getAccent(): Int {
        return ContextCompat.getColor(context, R.color.colorAccent)
    }
}
