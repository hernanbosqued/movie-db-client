package com.hernanbosqued.repo

import com.hernanbosqued.domain.model.ListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

internal interface APIService {
    @GET("list/{page}")
    fun getList(@Header("Authorization") authorization: String, @Path("page") page: Int): Call<ListModel>

    @GET("movie/popular")
    fun getMoviesPopular(@Header("Authorization") authorization: String, @Query("page") page: Int): Call<ListModel>

    @GET("movie/top_rated")
    fun getMoviesTopRated(@Header("Authorization") authorization: String, @Query("page") page: Int): Call<ListModel>

    @GET("tv/popular")
    fun getTVPopular(@Header("Authorization") authorization: String, @Query("page") page: Int): Call<ListModel>

    @GET("tv/top_rated")
    fun getTVTopRated(@Header("Authorization") authorization: String, @Query("page") page: Int): Call<ListModel>

    @GET("search/tv")
    fun searchTVShows(@Header("Authorization") authorization: String, @Query("page") page: Int, @Query("query")query: String): Call<ListModel>

    @GET("search/movie")
    fun searchMovies(@Header("Authorization") authorization: String, @Query("page") page: Int, @Query("query")query: String): Call<ListModel>

    @GET("search/multi")
    fun searchBoth(@Header("Authorization") authorization: String, @Query("page") page: Int, @Query("query")query: String): Call<ListModel>
}