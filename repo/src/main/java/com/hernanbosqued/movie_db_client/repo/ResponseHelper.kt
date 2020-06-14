package com.hernanbosqued.movie_db_client.repo

import com.hernanbosqued.movie_db_client.domain.ErrorModel
import com.hernanbosqued.movie_db_client.domain.ErrorsModel
import com.hernanbosqued.movie_db_client.domain.RepositoryCallbacks
import okhttp3.ResponseBody
import retrofit2.Response

internal class ResponseHelper<T> {
    private fun parseError(responseBody: ResponseBody): ErrorModel {
        val response = ServiceGenerator.parseResponse(ErrorsModel::class.java, responseBody)
        return if (response.errors.isNotEmpty()) {
            response.errors[0]
        } else {
            ErrorModel(Constants.CUSTOM_ERROR_CODE, Constants.NULL_ERROR_MESSAGE)
        }
    }

    fun processResponse(response: Response<T>, callbacks: RepositoryCallbacks<T>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                callbacks.onSuccess(body)
            } else {
                callbacks.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, Constants.NULL_RESPONSE_MESSAGE))
            }
        } else {
            callbacks.onFail(parseError(response.errorBody()!!))
        }
    }
}