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

    override fun getDetails(id: Int, callbacks: Client.ClientCallbackImpl<ListModel>) {
        service.getDetails(id, Constants.API_KEY)
            .enqueue(CallbackImpl(callbacks))

    }

    override fun getMoviesPopular(page: Int, callbacks: Client.ClientCallbackImpl<ListModel>) {
        service.getMoviesPopular(page, Constants.API_KEY)
            .enqueue(CallbackImpl(callbacks))

    }

    override fun getMoviesTopRated(page: Int, callbacks: Client.ClientCallbackImpl<ListModel>) {
        service.getMoviesTopRated(page, Constants.API_KEY)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun getTVPopular(page: Int, callbacks: Client.ClientCallbackImpl<ListModel>) {
        service.getTVPopular(page, Constants.API_KEY)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun getTVTopRated(page: Int, callbacks: Client.ClientCallbackImpl<ListModel>) {
        service.getTVTopRated(page, Constants.API_KEY)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun searchTVShows(page: Int, query: String?, callbacks: Client.ClientCallbackImpl<ListModel>) {
        service.searchTVShows(page, query, Constants.API_KEY)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun searchMovies(page: Int, query: String?, callbacks: Client.ClientCallbackImpl<ListModel>) {
        service.searchMovies(page, query, Constants.API_KEY)
            .enqueue(CallbackImpl(callbacks))
    }

    override fun searchBoth(page: Int, query: String?, callbacks: Client.ClientCallbackImpl<ListModel>) {
        service.searchBoth(page, query, Constants.API_KEY)
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