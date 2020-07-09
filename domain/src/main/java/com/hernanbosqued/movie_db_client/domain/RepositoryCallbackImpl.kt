package com.hernanbosqued.movie_db_client.domain

class RepositoryCallbackImpl<T : ResultModel>(
    private val title: String,
    private val query: String,
    private val callback: CarouselClientCallback
) :
    RepositoryCallback<ListModel<T>> {

    override fun onSuccess(data: ListModel<T>) {
        val model = CarouselModel().apply {
            this.title = this@RepositoryCallbackImpl.title
            this.query = this@RepositoryCallbackImpl.query
            this.page = data.page
            this.totalPages = data.totalPages
            this.totalResults = data.totalResults
            this.list = data.results?.map { it.parse() }?.toMutableList() ?: ArrayList()
        }
        callback.onOK(model)
    }

    override fun onFail(error: ErrorModel) {
        callback.onError(error.message)
    }
}