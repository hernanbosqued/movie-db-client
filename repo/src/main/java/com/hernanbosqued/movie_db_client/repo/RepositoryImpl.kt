package com.hernanbosqued.movie_db_client.repo

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hernanbosqued.movie_db_client.domain.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedInputStream
import java.io.BufferedReader
import java.io.InputStreamReader

class RepositoryImpl(val context: Context) : Repository {
    private var service: APIService = ServiceGenerator.createService(context, APIService::class.java)

    override fun carouselList(callbacks: RepositoryCallbacks<CarouselListModel>) {
        var stream = context.assets.open("repository.json")
        var reader = BufferedReader(InputStreamReader(stream))
        var result = Gson().fromJson(reader, CarouselListModel::class.java)

        callbacks.onSuccess(result)
    }

    override fun searchMovies(page: Int, query: String?, callbacks: RepositoryCallbacks<BaseListModel<MovieResultModel>>) {
        service.searchMovies(page, query, Constants.API_KEY).enqueue(APICallbacksImpl(callbacks))
    }

    override fun moviesPopular(page: Int, callbacks: RepositoryCallbacks<BaseListModel<MovieResultModel>>) {
        service.moviesPopular(page, Constants.API_KEY).enqueue(APICallbacksImpl(callbacks))
    }

    override fun moviesTopRated(page: Int, callbacks: RepositoryCallbacks<BaseListModel<MovieResultModel>>) {
        service.moviesTopRated(page, Constants.API_KEY).enqueue(APICallbacksImpl(callbacks))
    }

    override fun tvPopular(page: Int, callbacks: RepositoryCallbacks<BaseListModel<TVResultModel>>) {
        service.tvPopular(page, Constants.API_KEY).enqueue(APICallbacksImpl(callbacks))
    }

    override fun tvTopRated(page: Int, callbacks: RepositoryCallbacks<BaseListModel<TVResultModel>>) {
        service.tvTopRated(page, Constants.API_KEY).enqueue(APICallbacksImpl(callbacks))
    }

    override fun searchTV(page: Int, query: String?, callbacks: RepositoryCallbacks<BaseListModel<TVResultModel>>) {
        service.searchTVShows(page, query, Constants.API_KEY).enqueue(APICallbacksImpl(callbacks))
    }


    override fun searchBoth(page: Int, query: String?, callbacks: RepositoryCallbacks<BaseListModel<ResultModel>>) {
        service.searchBoth(page, query, Constants.API_KEY).enqueue(APICallbacksImpl(callbacks))
    }

    override fun videos(type: String, id: Int, callbacks: RepositoryCallbacks<BaseListModel<VideoResultModel>>) {
        service.videos(type, id, Constants.API_KEY).enqueue(APICallbacksImpl(callbacks))
    }

    class APICallbacksImpl<T>(private val callbacks: RepositoryCallbacks<T>) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                ResponseHelper<T>().processResponse(response, callbacks)
            } else {
                callbacks.onFail(ErrorModel(response.code(), Constants.HTTP_ERROR))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            callbacks.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, t.message ?: "onFailure"))
        }
    }
}