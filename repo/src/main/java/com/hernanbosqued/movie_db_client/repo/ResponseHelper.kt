package com.hernanbosqued.movie_db_client.repo

import com.hernanbosqued.movie_db_client.domain.ErrorModel
import com.hernanbosqued.movie_db_client.domain.ErrorsModel
import com.hernanbosqued.movie_db_client.domain.RepositoryCallback
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

    fun processResponse(response: Response<T>, callback: RepositoryCallback<T>) {
        if (response.isSuccessful) {
            val body = response.body()
            if (body != null) {
                callback.onSuccess(body)
            } else {
                callback.onFail(ErrorModel(Constants.CUSTOM_ERROR_CODE, Constants.NULL_RESPONSE_MESSAGE))
            }
        } else {
            callback.onFail(parseError(response.errorBody()!!))
        }
    }
}