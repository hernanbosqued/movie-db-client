package com.hernanbosqued.movie_db_client

import java.lang.ref.WeakReference

abstract class BasePresenter<M, V>(init_model: M){

    private var view: WeakReference<V>? = null

    var model: M = init_model
        set(value) {
            field = value
            if (setupDone()) {
                updateView()
            }
        }

    private fun setupDone(): Boolean {
        return model != null
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

    protected abstract fun updateView()
}