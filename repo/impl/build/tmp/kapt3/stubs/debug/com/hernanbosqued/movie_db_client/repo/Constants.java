package com.hernanbosqued.movie_db_client.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\f"}, d2 = {"Lcom/hernanbosqued/movie_db_client/repo/Constants;", "", "()V", "API_BASE_URL", "", "API_KEY", "CUSTOM_ERROR_CODE", "", "HTTP_ERROR", "IMAGE_BASE_URL", "NULL_ERROR_MESSAGE", "NULL_RESPONSE_MESSAGE", "repo_impl_debug"})
public final class Constants {
    public static final int CUSTOM_ERROR_CODE = -1;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NULL_ERROR_MESSAGE = "null error object";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String NULL_RESPONSE_MESSAGE = "null response object";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String API_BASE_URL = "https://api.themoviedb.org/3/";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String API_KEY = "42e3edfefb7fd2c0bb18118821379950";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HTTP_ERROR = "HTTP error";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w300";
    @org.jetbrains.annotations.NotNull()
    public static final com.hernanbosqued.movie_db_client.repo.Constants INSTANCE = null;
    
    private Constants() {
        super();
    }
}