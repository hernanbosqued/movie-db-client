package com.hernanbosqued.movie_db_client.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J(\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\t2\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\t2\b\b\u0001\u0010\b\u001a\u00020\tH\'J4\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\n\b\u0001\u0010\r\u001a\u0004\u0018\u00010\t2\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J(\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'J2\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00140\u00040\u00032\b\b\u0001\u0010\u0015\u001a\u00020\t2\b\b\u0001\u0010\u0016\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\tH\'\u00a8\u0006\u0017"}, d2 = {"Lcom/hernanbosqued/movie_db_client/repo/APIService;", "", "moviesPopular", "Lretrofit2/Call;", "Lcom/hernanbosqued/movie_db_client/domain/ListModel;", "Lcom/hernanbosqued/movie_db_client/domain/MovieResultModel;", "page", "", "apiKey", "", "moviesTopRated", "searchBoth", "Lcom/hernanbosqued/movie_db_client/domain/ResultModel;", "query", "searchMovies", "searchTVShows", "Lcom/hernanbosqued/movie_db_client/domain/TVResultModel;", "tvPopular", "tvTopRated", "videos", "Lcom/hernanbosqued/movie_db_client/domain/VideoResultModel;", "type", "id", "repo_impl_debug"})
public abstract interface APIService {
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "movie/popular")
    public abstract retrofit2.Call<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.MovieResultModel>> moviesPopular(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "movie/top_rated")
    public abstract retrofit2.Call<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.MovieResultModel>> moviesTopRated(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "tv/popular")
    public abstract retrofit2.Call<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.TVResultModel>> tvPopular(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "tv/top_rated")
    public abstract retrofit2.Call<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.TVResultModel>> tvTopRated(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "search/movie")
    public abstract retrofit2.Call<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.MovieResultModel>> searchMovies(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Query(value = "query")
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "search/tv")
    public abstract retrofit2.Call<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.TVResultModel>> searchTVShows(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Query(value = "query")
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "search/multi")
    public abstract retrofit2.Call<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.ResultModel>> searchBoth(@retrofit2.http.Query(value = "page")
    int page, @org.jetbrains.annotations.Nullable()
    @retrofit2.http.Query(value = "query")
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey);
    
    @org.jetbrains.annotations.NotNull()
    @retrofit2.http.GET(value = "{type}/{id}/videos")
    public abstract retrofit2.Call<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.VideoResultModel>> videos(@org.jetbrains.annotations.NotNull()
    @retrofit2.http.Path(value = "type")
    java.lang.String type, @retrofit2.http.Path(value = "id")
    int id, @org.jetbrains.annotations.NotNull()
    @retrofit2.http.Query(value = "api_key")
    java.lang.String apiKey);
}