package com.hernanbosqued.movie_db_client.ui

import android.content.Context

interface StringRepository {
    fun getA():String
    fun getB():String
    fun getC():String
}

class StringRepositoryImpl(private val context: Context) : StringRepository{

    override fun getA( ): String {
        return context.getString(R.string.a)
    }

    override fun getB(): String {
        return context.getString(R.string.b)
    }

    override fun getC(): String {
        return context.getString(R.string.c)
    }
}
