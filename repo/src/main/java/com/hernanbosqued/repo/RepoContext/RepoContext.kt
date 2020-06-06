package com.hernanbosqued.repo

import android.content.Context

object RepoContext {

    lateinit var context: Context

    fun load(context: Context) {
        this.context = context
    }
}
