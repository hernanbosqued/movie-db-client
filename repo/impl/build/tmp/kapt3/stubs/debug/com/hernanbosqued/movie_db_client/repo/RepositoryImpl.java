package com.hernanbosqued.movie_db_client.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001!B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\t\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016J$\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\fH\u0016J$\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\fH\u0016J.\u0010\u0014\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00110\fH\u0016J.\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00110\fH\u0016J.\u0010\u0019\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00110\fH\u0016J$\u0010\u001b\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00110\fH\u0016J$\u0010\u001c\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00110\fH\u0016J,\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u00102\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020 0\u00110\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\""}, d2 = {"Lcom/hernanbosqued/movie_db_client/repo/RepositoryImpl;", "Lcom/hernanbosqued/movie_db_client/domain/Repository;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "service", "Lcom/hernanbosqued/movie_db_client/repo/APIService;", "carouselList", "", "callback", "Lcom/hernanbosqued/movie_db_client/domain/RepositoryCallback;", "Lcom/hernanbosqued/movie_db_client/domain/CarouselListModel;", "moviesPopular", "page", "", "Lcom/hernanbosqued/movie_db_client/domain/ListModel;", "Lcom/hernanbosqued/movie_db_client/domain/MovieResultModel;", "moviesTopRated", "searchBoth", "query", "", "Lcom/hernanbosqued/movie_db_client/domain/ResultModel;", "searchMovies", "searchTV", "Lcom/hernanbosqued/movie_db_client/domain/TVResultModel;", "tvPopular", "tvTopRated", "videos", "type", "id", "Lcom/hernanbosqued/movie_db_client/domain/VideoResultModel;", "ServiceCallback", "repo_impl_debug"})
public final class RepositoryImpl implements com.hernanbosqued.movie_db_client.domain.Repository {
    private com.hernanbosqued.movie_db_client.repo.APIService service;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    @java.lang.Override()
    public void carouselList(@org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.CarouselListModel> callback) {
    }
    
    @java.lang.Override()
    public void searchMovies(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.MovieResultModel>> callback) {
    }
    
    @java.lang.Override()
    public void moviesPopular(int page, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.MovieResultModel>> callback) {
    }
    
    @java.lang.Override()
    public void moviesTopRated(int page, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.MovieResultModel>> callback) {
    }
    
    @java.lang.Override()
    public void tvPopular(int page, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.TVResultModel>> callback) {
    }
    
    @java.lang.Override()
    public void tvTopRated(int page, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.TVResultModel>> callback) {
    }
    
    @java.lang.Override()
    public void searchTV(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.TVResultModel>> callback) {
    }
    
    @java.lang.Override()
    public void searchBoth(int page, @org.jetbrains.annotations.Nullable()
    java.lang.String query, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.ResultModel>> callback) {
    }
    
    @java.lang.Override()
    public void videos(@org.jetbrains.annotations.NotNull()
    java.lang.String type, int id, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<com.hernanbosqued.movie_db_client.domain.ListModel<com.hernanbosqued.movie_db_client.domain.VideoResultModel>> callback) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.content.Context getContext() {
        return null;
    }
    
    @javax.inject.Inject()
    public RepositoryImpl(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0013\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u00a2\u0006\u0002\u0010\u0005J\u001e\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J$\u0010\f\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\t2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH\u0016R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000f"}, d2 = {"Lcom/hernanbosqued/movie_db_client/repo/RepositoryImpl$ServiceCallback;", "T", "Lretrofit2/Callback;", "repositoryCallback", "Lcom/hernanbosqued/movie_db_client/domain/RepositoryCallback;", "(Lcom/hernanbosqued/movie_db_client/domain/RepositoryCallback;)V", "onFailure", "", "call", "Lretrofit2/Call;", "t", "", "onResponse", "response", "Lretrofit2/Response;", "repo_impl_debug"})
    public static final class ServiceCallback<T extends java.lang.Object> implements retrofit2.Callback<T> {
        private final com.hernanbosqued.movie_db_client.domain.RepositoryCallback<T> repositoryCallback = null;
        
        @java.lang.Override()
        public void onResponse(@org.jetbrains.annotations.NotNull()
        retrofit2.Call<T> call, @org.jetbrains.annotations.NotNull()
        retrofit2.Response<T> response) {
        }
        
        @java.lang.Override()
        public void onFailure(@org.jetbrains.annotations.NotNull()
        retrofit2.Call<T> call, @org.jetbrains.annotations.NotNull()
        java.lang.Throwable t) {
        }
        
        public ServiceCallback(@org.jetbrains.annotations.NotNull()
        com.hernanbosqued.movie_db_client.domain.RepositoryCallback<T> repositoryCallback) {
            super();
        }
    }
}