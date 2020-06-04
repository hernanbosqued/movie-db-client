package com.hernanbosqued.repo

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
            .enqueue(object : Callback<ListModel> {
                override fun onResponse(call: Call<ListModel>, response: Response<ListModel>) {
                    if (response.isSuccessful) {
                        ResponseHelper<ListModel>().processResponse(response, callbacks)
                    } else {
                        callbacks.onFail(ErrorModel(response.code(), Constants.HTTP_ERROR))
                    }
                }

                override fun onFailure(call: Call<ListModel>, t: Throwable) {
                    callbacks.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, t.message!!))
                }
            })
    }

    override fun getMoviesPopular(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getMoviesPopular(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(object : Callback<ListModel> {
                override fun onResponse(call: Call<ListModel>, response: Response<ListModel>) {
                    if (response.isSuccessful) {
                        ResponseHelper<ListModel>().processResponse(response, callbacks)
                    } else {
                        callbacks.onFail(ErrorModel(response.code(), Constants.HTTP_ERROR))
                    }
                }

                override fun onFailure(call: Call<ListModel>, t: Throwable) {
                    callbacks.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, t.message!!))
                }
            })
    }

    override fun getTVPopular(page: Int, callbacks: ModelCallbacks<ListModel>) {
        service.getTVPopular(Constants.AUTH_PREFIX + Constants.TOKEN, page)
            .enqueue(object : Callback<ListModel> {
                override fun onResponse(call: Call<ListModel>, response: Response<ListModel>) {
                    if (response.isSuccessful) {
                        ResponseHelper<ListModel>().processResponse(response, callbacks)
                    } else {
                        callbacks.onFail(ErrorModel(response.code(), Constants.HTTP_ERROR))
                    }
                }

                override fun onFailure(call: Call<ListModel>, t: Throwable) {
                    callbacks.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, t.message!!))
                }
            })
    }
}