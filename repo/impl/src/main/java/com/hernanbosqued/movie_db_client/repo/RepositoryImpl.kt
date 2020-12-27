package com.hernanbosqued.movie_db_client.repo

import android.content.Context
import com.google.gson.Gson
import com.hernanbosqued.movie_db_client.domain.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Inject

class RepositoryImpl @Inject constructor(val context: Context) : Repository {
    private var service: APIService = ServiceGenerator.createService(context, APIService::class.java)

    override fun carouselList(callback: RepositoryCallback<CarouselListModel>) {
        val stream = context.assets.open("repository.json")
        val reader = BufferedReader(InputStreamReader(stream))
        val result = Gson().fromJson(reader, CarouselListModel::class.java)

        callback.onSuccess(result)
    }

    override fun searchMovies(page: Int, query: String?, callback: RepositoryCallback<ListModel<MovieResultModel>>) {
        service.searchMovies(page, query, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    override fun moviesPopular(page: Int, callback: RepositoryCallback<ListModel<MovieResultModel>>) {
        service.moviesPopular(page, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    override fun moviesTopRated(page: Int, callback: RepositoryCallback<ListModel<MovieResultModel>>) {
        service.moviesTopRated(page, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    override fun tvPopular(page: Int, callback: RepositoryCallback<ListModel<TVResultModel>>) {
        service.tvPopular(page, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    override fun tvTopRated(page: Int, callback: RepositoryCallback<ListModel<TVResultModel>>) {
        service.tvTopRated(page, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    override fun searchTV(page: Int, query: String?, callback: RepositoryCallback<ListModel<TVResultModel>>) {
        service.searchTVShows(page, query, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    override fun searchBoth(page: Int, query: String?, callback: RepositoryCallback<ListModel<ResultModel>>) {
        service.searchBoth(page, query, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    override fun tvDetails(id: Int, callback: RepositoryCallback<TVDetailsModel>) {
        service.tvDetails(id, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    override fun videos(type: String, id: Int, callback: RepositoryCallback<ListModel<VideoResultModel>>) {
        service.videos(type, id, Constants.API_KEY).enqueue(ServiceCallback(callback))
    }

    class ServiceCallback<T>(private val repositoryCallback: RepositoryCallback<T>) : Callback<T> {

        override fun onResponse(call: Call<T>, response: Response<T>) {
            if (response.isSuccessful) {
                ResponseHelper<T>().processResponse(response, repositoryCallback)
            } else {
                repositoryCallback.onFail(ErrorModel(response.code(), Constants.HTTP_ERROR))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            repositoryCallback.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, t.message ?: "onFailure"))
        }
    }
}