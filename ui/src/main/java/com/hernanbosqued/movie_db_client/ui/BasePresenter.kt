package com.hernanbosqued.movie_db_client.ui

import java.lang.ref.WeakReference

abstract class BasePresenter<M, V>() {

    constructor(view: V) : this() {
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

    fun model(): M? {
        return model
    }

    fun bindModel(model: M) {
        this.model = model
    }

    fun view(): V? {
        return view?.get()
    }

    fun unbindView() {
        view = null
    }
}