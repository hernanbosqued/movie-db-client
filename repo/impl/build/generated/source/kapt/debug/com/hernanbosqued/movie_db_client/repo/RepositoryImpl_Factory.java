// Generated by Dagger (https://dagger.dev).
package com.hernanbosqued.movie_db_client.repo;

import android.content.Context;
import dagger.internal.Factory;
import javax.inject.Provider;

@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class RepositoryImpl_Factory implements Factory<RepositoryImpl> {
  private final Provider<Context> contextProvider;

  public RepositoryImpl_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public RepositoryImpl get() {
    return newInstance(contextProvider.get());
  }

  public static RepositoryImpl_Factory create(Provider<Context> contextProvider) {
    return new RepositoryImpl_Factory(contextProvider);
  }

  public static RepositoryImpl newInstance(Context context) {
    return new RepositoryImpl(context);
  }
}
