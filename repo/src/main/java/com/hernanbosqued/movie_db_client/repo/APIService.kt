package com.hernanbosqued.movie_db_client.repo

import com.hernanbosqued.movie_db_client.domain.model.ListModel
import com.hernanbosqued.movie_db_client.domain.model.MovieListModel
import com.hernanbosqued.movie_db_client.domain.model.TVListModel
import com.hernanbosqued.movie_db_client.domain.model.VideoModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    @GET("movie/popular")
    fun getMoviesPopular(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<MovieListModel>

    @GET("movie/top_rated")
    fun getMoviesTopRated(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<MovieListModel>

    @GET("search/movie")
    fun searchMovies(@Query("page") page: Int, @Query("query") query: String?, @Query("api_key") apiKey: String): Call<MovieListModel>


    @GET("tv/popular")
    fun getTVPopular(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<TVListModel>

    @GET("tv/top_rated")
    fun getTVTopRated(@Query("page") page: Int, @Query("api_key") apiKey: String): Call<TVListModel>

    @GET("search/tv")
    fun searchTVShows(@Query("page") page: Int, @Query("query") query: String?, @Query("api_key") apiKey: String): Call<TVListModel>


    @GET("search/multi")
    fun searchBoth(@Query("page") page: Int, @Query("query") query: String?, @Query("api_key") apiKey: String): Call<ListModel>

    @GET("{type}/{id}/videos")
    fun getVideos(@Path("type") type: String, @Path("id") id: Int, @Query("api_key") apiKey: String): Call<VideoModel>
}