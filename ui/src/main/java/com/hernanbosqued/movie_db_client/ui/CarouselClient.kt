package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.*

class CarouselClient(private var repository: Repository) {

    fun searchBoth(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.searchBoth(page, query, RepositoryCallbacksImpl(callbacks))
    }

    fun searchTV(page: Int, query: String, callbacks: CarouselClientCallbacks) {
        repository.searchTV(page, query, RepositoryCallbacksImpl(callbacks))
    }

    fun searchMovies(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.searchMovies(page, query, RepositoryCallbacksImpl(callbacks))
    }

    fun moviesPopular(page: Int, callbacks: CarouselClientCallbacks) {
        repository.moviesPopular(page, RepositoryCallbacksImpl(callbacks))
    }

    fun moviesTopRated(page: Int, callbacks: CarouselClientCallbacks) {
        repository.moviesTopRated(page, RepositoryCallbacksImpl(callbacks))
    }

    fun tvPopular(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.tvPopular(page, RepositoryCallbacksImpl(callbacks))
    }
}


class RepositoryCallbacksImpl<T : ResultModel>(var callbacks: CarouselClientCallbacks) : RepositoryCallbacks<BaseListModel<T>> {

    override fun onSuccess(data: BaseListModel<T>) {
        callbacks.onOK(data.results.map { it.parse() })
    }

    override fun onFail(error: ErrorModel) {
        callbacks.onError(error.message)
    }
}

//        repository.run {
//            tvPopular(page, object : Callbacks.RepositoryCallbacks<BaseListModel<TVResultModel>) {
//                override fun onFail(errorModel: ErrorModel) {
//                    TODO("Not yet implemented")
//                }
//
//        }

//    fun videos(type: String, id: Int, callbacks: Callbacks.CarouselClientCallbacks tCallbacks) {
//        repository.videos(type, id, GenericsClientCallbackImpl<VideoResultModel>(callbacks))
//    }

//val visitor: CarouselVisitor = CarouselVisitor(callbacks)
//        fun get(): Callbacks.RepositoryCallbacks<BaseListModel<U>> {
//            return object : Callbacks.RepositoryCallbacks<BaseListModel<ResultModel>> {
//
//                override fun onSuccess(data: BaseListModel<ResultModel>) {
//
//                    CarouselClient::totalPages.set()
//
//                    data.results.forEach { t -> t.visit(visitor) }
//                }
//
//                override fun onFail(errorModel: ErrorModel) {
//                     callbacks.onError(errorModel.message)
//                }
//            }
//        }
