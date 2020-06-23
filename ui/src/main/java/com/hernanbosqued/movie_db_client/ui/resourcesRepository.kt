package com.hernanbosqued.movie_db_client.ui

import android.content.Context
import androidx.core.content.ContextCompat

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
}

class ResourcesRepositoryImpl(private val context: Context) : ResourcesRepository{

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

    override fun searchBoth( ): String {
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
