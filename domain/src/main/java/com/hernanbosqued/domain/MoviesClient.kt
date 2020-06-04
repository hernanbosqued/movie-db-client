package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ErrorModel
import com.hernanbosqued.domain.model.ListModel

class MoviesClient(val repo: MoviesRepository) {

    interface Callback {
        fun onOK(model: ListModel)
        fun onError(error: String)
    }

    fun getList(page: Int, callback: Callback) {
        repo.getList(page, object : ModelCallback<ListModel> {
            override fun onSuccess(data: ListModel) {
                callback.onOK(data)
            }

            override fun onFail(errorModel: ErrorModel) {
                callback.onError(errorModel.message)
            }
        })
    }

}