package com.hernanbosqued.movie_db_client.repo

import android.content.Context
import com.hernanbosqued.movie_db_client.domain.ModelCallbacks
import com.hernanbosqued.movie_db_client.domain.Repository
import com.hernanbosqued.movie_db_client.domain.model.ErrorModel
import com.hernanbosqued.movie_db_client.domain.model.ListModel
import com.hernanbosqued.movie_db_client.domain.model.VideoModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryImpl(context : Context) : Repository {
    private var service: APIService = ServiceGenerator.createService(context, APIService::class.java)

    override fun getMoviesPopular(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getMoviesPopular(page, Constants.API_KEY).enqueue(CallbackImpl(callbacks))
    }

    override fun getMoviesTopRated(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getMoviesTopRated(page, Constants.API_KEY).enqueue(CallbackImpl(callbacks))
    }

    override fun getTVPopular(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getTVPopular(page, Constants.API_KEY).enqueue(CallbackImpl(callbacks))
    }

    override fun getTVTopRated(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getTVTopRated(page, Constants.API_KEY).enqueue(CallbackImpl(callbacks))
    }

    override fun searchTVShows(page: Int, query: String?, callbacks: ModelCallbacks<ListModel>) {
        service.searchTVShows(page, query, Constants.API_KEY).enqueue(CallbackImpl(callbacks))
    }

    override fun searchMovies(page: Int, query: String?, callbacks: ModelCallbacks<ListModel>) {
        service.searchMovies(page, query, Constants.API_KEY).enqueue(CallbackImpl(callbacks))
    }

    override fun searchBoth(page: Int, query: String?, callbacks: ModelCallbacks<ListModel>) {
        service.searchBoth(page, query, Constants.API_KEY).enqueue(CallbackImpl(callbacks))
    }

    override fun getVideos(type: String, id: Int, callbacks: ModelCallbacks<VideoModel>) {
        service.getVideos(type, id, Constants.API_KEY).enqueue(CallbackImpl(callbacks))
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