package com.hernanbosqued.movie_db_client

import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.ClientCallbacks
import com.hernanbosqued.domain.model.ListModel
import com.hernanbosqued.repo.RepositoryImpl

class DetailPresenter(view: DetailContract.View) : BasePresenter<String, DetailContract.View>(view), ClientCallbacks<ListModel> {

    init {
        Client.repo = RepositoryImpl
    }

    fun getDetails(id: Int) {
        Client.getDetails(id,this)
    }

    override fun onOK(response: ListModel) {
    }

    override fun onError(error: String) {
    }
}
