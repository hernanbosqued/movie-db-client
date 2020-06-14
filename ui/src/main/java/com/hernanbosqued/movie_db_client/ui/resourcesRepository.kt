package com.hernanbosqued.movie_db_client.ui

import android.content.Context
import androidx.core.content.ContextCompat

interface ResourcesRepository {
    fun searchBoth():String
    fun searchMovies():String
    fun searchTV():String
    fun getD():String
    fun getAccent():Int
}

class ResourcesRepositoryImpl(private val context: Context) : ResourcesRepository{

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
