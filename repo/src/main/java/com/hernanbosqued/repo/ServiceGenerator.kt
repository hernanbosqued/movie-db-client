package com.hernanbosqued.repo

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceGenerator{
    private val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY;

        val gson = GsonBuilder().setLenient().create()
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl(Constants.BASE_API)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    fun <T> createService(serviceClass: Class<T>?): T {
        return retrofit.create(serviceClass)
    }

    fun <T> parseResponse(type: Class<T>, responseBody: ResponseBody?): T {
        val responseBodyObjectConverter: Converter<ResponseBody, T> = retrofit.responseBodyConverter(type, arrayOfNulls(0))
        return responseBodyObjectConverter.convert(responseBody)

    }
}