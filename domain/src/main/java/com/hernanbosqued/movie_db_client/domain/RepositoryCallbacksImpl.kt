package com.hernanbosqued.movie_db_client.domain

class RepositoryCallbacksImpl<T : ResultModel>(
    private val title: String,
    private val callbacks: CarouselClientCallbacks
) :
    RepositoryCallbacks<ListModel<T>> {

    override fun onSuccess(data: ListModel<T>) {
        val model = CarouselModel().apply {
            this.title = this@RepositoryCallbacksImpl.title
            this.page = data.page
            this.totalPages = data.totalPages
            this.totalResults = data.totalResults
            this.list = data.results?.map { it.parse() }?.toMutableList() ?: ArrayList()
        }
        callbacks.onOK(model)
    }

    override fun onFail(error: ErrorModel) {
        callbacks.onError(error.message)
    }
}