package com.hernanbosqued.movie_db_client.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u0005\u00a2\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0002J\"\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\r\u00a8\u0006\u000e"}, d2 = {"Lcom/hernanbosqued/movie_db_client/repo/ResponseHelper;", "T", "", "()V", "parseError", "Lcom/hernanbosqued/movie_db_client/domain/ErrorModel;", "responseBody", "Lokhttp3/ResponseBody;", "processResponse", "", "response", "Lretrofit2/Response;", "callback", "Lcom/hernanbosqued/movie_db_client/domain/RepositoryCallback;", "repo_impl_debug"})
public final class ResponseHelper<T extends java.lang.Object> {
    
    private final com.hernanbosqued.movie_db_client.domain.ErrorModel parseError(okhttp3.ResponseBody responseBody) {
        return null;
    }
    
    public final void processResponse(@org.jetbrains.annotations.NotNull()
    retrofit2.Response<T> response, @org.jetbrains.annotations.NotNull()
    com.hernanbosqued.movie_db_client.domain.RepositoryCallback<T> callback) {
    }
    
    public ResponseHelper() {
        super();
    }
}