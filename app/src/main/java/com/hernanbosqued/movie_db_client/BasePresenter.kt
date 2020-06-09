package com.hernanbosqued.movie_db_client

import java.lang.ref.WeakReference

abstract class BasePresenter<M, V>(init_model: M) {

    var model: M = init_model

    private var view: WeakReference<V>? = null

    private fun setupDone(): Boolean {
        return model != null && view != null
    }

    open fun bindView(view: V) {
        this.view = WeakReference(view)
    }

    fun view(): V? {
        return view?.get()
    }

    fun unbindView() {
        view = null
    }
}