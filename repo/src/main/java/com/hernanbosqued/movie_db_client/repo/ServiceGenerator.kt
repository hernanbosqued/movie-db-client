package com.hernanbosqued.movie_db_client.repo

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.google.gson.GsonBuilder
import com.hernanbosqued.movie_db_client.domain.ResultModel
import okhttp3.*
import okhttp3.CacheControl
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

object ServiceGenerator {

    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"
    private var cache: Cache? = null
    private lateinit var retrofit: Retrofit

    private fun init(context: Context) {
        val interceptor = HttpLoggingInterceptor()

        val dispatcher = Dispatcher()
        dispatcher.maxRequests = 10

        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val gson = GsonBuilder()
            .registerTypeAdapter(ResultModel::class.java, ResultModel.Deserializer)
            .setLenient().create()

        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .addInterceptor(provideOfflineCacheInterceptor(context))
            .addNetworkInterceptor(provideCacheInterceptor(context))
            .cache(provideCache(context))
            .dispatcher(dispatcher)
            .build()

        retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(Constants.API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun provideCacheInterceptor(context: Context): Interceptor {
        return Interceptor { chain ->
            val response = chain.proceed(chain.request())

            val cacheControl: CacheControl = if (isConnected(context)) {
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

    private fun provideCache(context: Context): Cache {
        cache?: run{
            val file = File(context.filesDir, "http_cache")
            this.cache = Cache(file, 100 * 1024 * 1024)
        }
        return this.cache!!
    }

    private fun provideOfflineCacheInterceptor(context: Context): Interceptor {
        return Interceptor { chain: Interceptor.Chain ->

            var request: Request = chain.request()

            if (!isConnected(context)) {
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

    private fun isConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.allNetworks.forEach { network ->
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            if (capabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true) {
                return true
            }
        }
        return false
    }

    fun <T> createService(context: Context, serviceClass: Class<T>): T {
        init(context)
        return retrofit.create(serviceClass)
    }

    fun <T> parseResponse(type: Class<T>, responseBody: ResponseBody): T {
        val responseBodyObjectConverter: Converter<ResponseBody, T> = retrofit.responseBodyConverter(type, arrayOfNulls(0))
        return responseBodyObjectConverter.convert(responseBody)!!
    }
}