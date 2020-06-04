package com.hernanbosqued.repo

import com.hernanbosqued.domain.ModelCallback
import com.hernanbosqued.domain.MoviesRepository
import com.hernanbosqued.domain.model.ErrorModel
import com.hernanbosqued.domain.model.ListModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MoviesRepositoryImpl : MoviesRepository {
    private val service: APIService = ServiceGenerator.createService(APIService::class.java)

    override fun getList(page: Int, callback: ModelCallback<ListModel>) {
        service.getList(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(object : Callback<ListModel> {
                override fun onResponse(call: Call<ListModel>, response: Response<ListModel>) {
                    if (response.isSuccessful) {
                        ResponseHelper<ListModel>().processResponse(response, callback)
                    } else {
                        callback.onFail(ErrorModel(response.code(), Constants.HTTP_ERROR))
                    }
                }

                override fun onFailure(call: Call<ListModel>, t: Throwable) {
                    callback.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, t.message!!))
                }
            })
    }

    override fun getMoviesPopular(page: Int, callback: ModelCallback<ListModel>) {
        service.getMoviesPopular(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(object : Callback<ListModel> {
                override fun onResponse(call: Call<ListModel>, response: Response<ListModel>) {
                    if (response.isSuccessful) {
                        ResponseHelper<ListModel>().processResponse(response, callback)
                    } else {
                        callback.onFail(ErrorModel(response.code(), Constants.HTTP_ERROR))
                    }
                }

                override fun onFailure(call: Call<ListModel>, t: Throwable) {
                    callback.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, t.message!!))
                }
            })
    }
}