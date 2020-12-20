package com.hernanbosqued.movie_db_client.repo;

import java.lang.System;

@kotlin.Metadata(mv = {1, 4, 1}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\'\u0010\n\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000f\u00a2\u0006\u0002\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\f\u001a\u00020\rH\u0002J\'\u0010\u0015\u001a\u0002H\u000b\"\u0004\b\u0000\u0010\u000b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000f2\u0006\u0010\u0017\u001a\u00020\u0018\u00a2\u0006\u0002\u0010\u0019J\u0010\u0010\u001a\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\rH\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/hernanbosqued/movie_db_client/repo/ServiceGenerator;", "", "()V", "HEADER_CACHE_CONTROL", "", "HEADER_PRAGMA", "cache", "Lokhttp3/Cache;", "retrofit", "Lretrofit2/Retrofit;", "createService", "T", "context", "Landroid/content/Context;", "serviceClass", "Ljava/lang/Class;", "(Landroid/content/Context;Ljava/lang/Class;)Ljava/lang/Object;", "init", "", "isConnected", "", "parseResponse", "type", "responseBody", "Lokhttp3/ResponseBody;", "(Ljava/lang/Class;Lokhttp3/ResponseBody;)Ljava/lang/Object;", "provideCache", "provideOfflineCacheInterceptor", "Lokhttp3/Interceptor;", "repo_impl_debug"})
public final class ServiceGenerator {
    private static final java.lang.String HEADER_CACHE_CONTROL = "Cache-Control";
    private static final java.lang.String HEADER_PRAGMA = "Pragma";
    private static okhttp3.Cache cache;
    private static retrofit2.Retrofit retrofit;
    @org.jetbrains.annotations.NotNull()
    public static final com.hernanbosqued.movie_db_client.repo.ServiceGenerator INSTANCE = null;
    
    private final void init(android.content.Context context) {
    }
    
    private final okhttp3.Cache provideCache(android.content.Context context) {
        return null;
    }
    
    private final okhttp3.Interceptor provideOfflineCacheInterceptor(android.content.Context context) {
        return null;
    }
    
    private final boolean isConnected(android.content.Context context) {
        return false;
    }
    
    public final <T extends java.lang.Object>T createService(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.Class<T> serviceClass) {
        return null;
    }
    
    public final <T extends java.lang.Object>T parseResponse(@org.jetbrains.annotations.NotNull()
    java.lang.Class<T> type, @org.jetbrains.annotations.NotNull()
    okhttp3.ResponseBody responseBody) {
        return null;
    }
    
    private ServiceGenerator() {
        super();
    }
}