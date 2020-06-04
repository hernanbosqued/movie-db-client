package com.hernanbosqued.domain

import com.hernanbosqued.domain.model.ErrorModel
import com.hernanbosqued.domain.model.ListModel

class MoviesClient(val repo: Repository) : Client {
    override fun get(page: Int, callback: ClientCallbacks) {
        repo.getMoviesPopular(page, object : ModelCallbacks<ListModel> {
            override fun onSuccess(data: ListModel) {
                callback.onOK(data)
            }

            override fun onFail(errorModel: ErrorModel) {
                callback.onError(errorModel.code.toString() + " - " + errorModel.message)
            }
        })
    }
}