package com.hernanbosqued.repo

import com.hernanbosqued.domain.Client
import com.hernanbosqued.domain.ModelCallbacks
import com.hernanbosqued.domain.Repository
import com.hernanbosqued.domain.model.ErrorModel
import com.hernanbosqued.domain.model.ListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object RepositoryImpl : Repository {
    private val service: APIService = ServiceGenerator.createService(APIService::class.java)

    override fun getList(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getList(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun getMoviesPopular(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getMoviesPopular(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(CallbackImpl(callbacks))

    }

    override fun getMoviesTopRated(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getMoviesTopRated(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun getTVPopular(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getTVPopular(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun getTVTopRated(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getTVTopRated(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun searchTVShows(page: Int, query: String, callbacks: Client.ModelCallbacksImpl<ListModel>) {
        service.searchTVShows(Constants.AUTH_PREFIX + Constants.TOKEN, page, query)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun searchMovies(page: Int, query: String, callbacks: Client.ModelCallbacksImpl<ListModel>) {
        service.searchMovies(Constants.AUTH_PREFIX + Constants.TOKEN, page, query)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun searchBoth(page: Int, query: String, callbacks: Client.ModelCallbacksImpl<ListModel>) {
        service.searchBoth(Constants.AUTH_PREFIX + Constants.TOKEN, page, query)
            .enqueue(CallbackImpl(callbacks))
    }

    class CallbackImpl<T>(private val modelCallbacks: ModelCallbacks<T>) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                ResponseHelper<T>().processResponse(response, modelCallbacks)
            } else {
                modelCallbacks.onFail(ErrorModel(response.code(), Constants.HTTP_ERROR))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            modelCallbacks.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, t.message!!))
        }
    }
}