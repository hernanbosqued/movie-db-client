package com.hernanbosqued.movie_db_client.ui

import com.hernanbosqued.movie_db_client.domain.*

class CarouselClient(private var repository: Repository) {

    private var page: Int = 0

    fun searchBoth(query: String?, callbacks: CarouselClientCallbacks) {
        repository.searchBoth(page, query, RepositoryCallbacksImpl<BaseListModel<ResultModel>>())
    }

    fun searchTV(page: Int, query: String, callbacks: CarouselClientCallbacks) {
        repository.searchTV(page, query, GenericsClientCallbackImpl<TVResultModel>(callbacks))
    }

    fun searchMovies(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.searchMovies(page, query, GenericsClientCallbackImpl<MovieResultModel>(callbacks))
    }

    fun moviesPopular(page: Int, callbacks: CarouselClientCallbacks) {
        repository.moviesPopular(page, GenericsClientCallbackImpl<MovieResultModel>(callbacks))
    }

    fun moviesTopRated(page: Int, callbacks: CarouselClientCallbacks) {
        repository.moviesTopRated(page, GenericsClientCallbackImpl<MovieResultModel>(callbacks))
    }

    fun tvPopular(page: Int, query: String?, callbacks: CarouselClientCallbacks) {
        repository.tvPopular(page, GenericsClientCallbackImpl<TVResultModel>(callbacks))
    }
}

class GenericsClientCallbackImpl<T : ResultModel>(callbacks: CarouselClientCallbacks) : RepositoryCallbacks<BaseListModel<T>> {
    override fun onSuccess(data: BaseListModel<T>) {
    }

    override fun onFail(error: ErrorModel) {
    }
}

class RepositoryCallbacksImpl<T> : RepositoryCallbacks<T> {
    override fun onSuccess(data: T) {
    }

    override fun onFail(error: ErrorModel) {

    }
}

//        repository.run {
//            tvPopular(page, object : Callbacks.RepositoryCallbacks<BaseListModel<TVResultModel>) {
//                override fun onFail(errorModel: ErrorModel) {
//                    TODO("Not yet implemented")
//                }
//            }
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
//                    callbacks.onError(errorModel.message)
//                }
//            }
//        }
