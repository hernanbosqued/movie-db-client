package com.hernanbosqued.repo

import android.content.Context
import android.net.ConnectivityManager
import com.google.gson.GsonBuilder
import okhttp3.*
import okhttp3.CacheControl
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

infix fun <T> T?.ifNull(block: () -> Unit) {
    if (this == null) block()
}

object ServiceGenerator {
    public const val HEADER_CACHE_CONTROL = "Cache-Control"
    public const val HEADER_PRAGMA = "Pragma"
    public var cache: Cache? = null
    public val retrofit: Retrofit

    init {
        val interceptor = HttpLoggingInterceptor()

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 10

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val gson = GsonBuilder().setLenient().create()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(provideOfflineCacheInterceptor())
            .addNetworkInterceptor(provideCacheInterceptor())
            .cache(provideCache())
            .dispatcher(dispatcher)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    public fun provideCacheInterceptor(): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl: CacheControl = if (isConnected()) {
                CacheControl.Builder()
                    .maxAge(60, TimeUnit.SECONDS)
                    .build()
            } else {
                CacheControl.Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()
            }

            response.newBuilder()
                .removeHeader(HEADER_PRAGMA)
                .removeHeader(HEADER_CACHE_CONTROL)
                .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                .build()
        }
    }

    public fun provideCache(): Cache {
        cache.ifNull {
            val file = File(RepoContext.context.filesDir, "http_cache")
            this.cache = Cache(file, 100 * 1024 * 1024)
        }
        return this.cache!!
    }

    public fun provideOfflineCacheInterceptor(): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->

            var request: Request = chain.request()

            if (!isConnected()) {
                val cacheControl = CacheControl
                    .Builder()
                    .maxStale(7, TimeUnit.DAYS)
                    .build()

                request = request
                    .newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .cacheControl(cacheControl)
                    .build()
            }
            chain.proceed(request)
        }
    }

    public fun isConnected(): Boolean {
        val systemService = RepoContext.context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = systemService.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }

    fun <T> parseResponse(type: Class<T>, responseBody: ResponseBody): T {
        val responseBodyObjectConverter: Converter<ResponseBody, T> = retrofit.responseBodyConverter(type, arrayOfNulls(0))
        return responseBodyObjectConverter.convert(responseBody)!!
    }
}