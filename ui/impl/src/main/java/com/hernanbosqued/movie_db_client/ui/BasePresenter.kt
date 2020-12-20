package com.hernanbosqued.movie_db_client.ui

import java.lang.ref.WeakReference

abstract class BasePresenter<MODEL, VIEW>(private var model: MODEL, view: VIEW) {

    private var weakView = WeakReference<VIEW>(view)

    fun model(): MODEL {
        return model
    }

    fun view(): VIEW {
        return weakView.get()!!
    }

    fun bindModel(model: MODEL){
        this.model = model
    }

    fun bindView(view: VIEW){
        this.weakView = WeakReference(view)
    }

    fun unbindView() {
        weakView.clear()
    }
}