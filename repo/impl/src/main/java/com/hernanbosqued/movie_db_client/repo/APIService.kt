package com.hernanbosqued.movie_db_client.repo

import com.hernanbosqued.movie_db_client.domain.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    fun moviesPopular(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<ListModel<MovieResultModel>>

    @GET("movie/top_rated")
    fun moviesTopRated(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<ListModel<MovieResultModel>>

    @GET("tv/popular")
    fun tvPopular(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<ListModel<TVResultModel>>

    @GET("tv/top_rated")
    fun tvTopRated(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<ListModel<TVResultModel>>

    @GET("tv/{id}")
    fun tvDetails(@Path("id") id: Int, @Query("api_key") apiKey: String): Call<TVDetailsModel>

    @GET("tv/{id}/season/{season_number}")
    fun tvSeasonDetails(@Path("id") id: Int, @Path("season_number") seasonNumber: Int, @Query("api_key") apiKey: String): Call<SeasonModel>

    @GET("search/movie")
    fun searchMovies(@Query("page") page: Int, @Query("query") query: String?, @Query("api_key") apiKey: String): Call<ListModel<MovieResultModel>>

    @GET("search/tv")
    fun searchTVShows(@Query("page") page: Int, @Query("query") query: String?, @Query("api_key") apiKey: String): Call<ListModel<TVResultModel>>

    @GET("search/multi")
    fun searchBoth(@Query("page") page: Int, @Query("query") query: String?, @Query("api_key") apiKey: String): Call<ListModel<ResultModel>>


    @GET("{type}/{id}/videos")
    fun videos(@Path("type") type: String, @Path("id") id: Int, @Query("api_key") apiKey: String): Call<ListModel<VideoResultModel>>
}