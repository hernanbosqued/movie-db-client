package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ErrorModel
import com.hernanbosqued.domain.model.ListModel

object Client {

    lateinit var repo: Repository

    fun getMoviesPopular(page: Int, callback: ClientCallbacks) {
        repo.getMoviesPopular(page, object : ModelCallbacks<ListModel> {
            override fun onSuccess(data: ListModel) {
                callback.onOK(data)
            }

            override fun onFail(errorModel: ErrorModel) {
                callback.onError(errorModel.code.toString() + " - " + errorModel.message)
            }
        })
    }

    fun getMoviesTopRated(page: Int, callback: ClientCallbacks) {
        repo.getMoviesTopRated(page, object : ModelCallbacks<ListModel> {
            override fun onSuccess(data: ListModel) {
                callback.onOK(data)
            }

            override fun onFail(errorModel: ErrorModel) {
                callback.onError(errorModel.code.toString() + " - " + errorModel.message)
            }
        })
    }

    fun getList(page: Int, callback: ClientCallbacks) {
        repo.getList(page, object : ModelCallbacks<ListModel> {
            override fun onSuccess(data: ListModel) {
                callback.onOK(data)
            }

            override fun onFail(errorModel: ErrorModel) {
                callback.onError(errorModel.code.toString() + " - " + errorModel.message)
            }
        })
    }

    fun getTVPopular(page: Int, callback: ClientCallbacks) {
        repo.getTVPopular(page, object : ModelCallbacks<ListModel> {
            override fun onSuccess(data: ListModel) {
                callback.onOK(data)
            }

            override fun onFail(errorModel: ErrorModel) {
                callback.onError(errorModel.code.toString() + " - " + errorModel.message)
            }
        })
    }

    fun getTVTopRated(page: Int, callback: ClientCallbacks) {
        repo.getTVTopRated(page, object : ModelCallbacks<ListModel> {
            override fun onSuccess(data: ListModel) {
                callback.onOK(data)
            }

            override fun onFail(errorModel: ErrorModel) {
                callback.onError(errorModel.code.toString() + " - " + errorModel.message)
            }
        })
    }
}