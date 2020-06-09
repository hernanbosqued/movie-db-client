package com.hernanbosqued.movie_db_client

import java.lang.ref.WeakReference

abstract class BasePresenter<M, V>(view: V){

    init {
        bindView(view)
    }

    var model: M? = null

    private var view: WeakReference<V>? = null

    private fun setupDone(): Boolean {
        return model != null && view != null
    }

    fun bindView(view: V) {
        this.view = WeakReference(view)
    }

    fun view(): V? {
        return view?.get()
    }

    fun unbindView() {
        view = null
    }
}