package com.example.task2module6.data.repository;

import com.example.task2module6.data.remote.api.NobelApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class NobelRepositoryImpl_Factory implements Factory<NobelRepositoryImpl> {
  private final Provider<NobelApi> apiProvider;

  public NobelRepositoryImpl_Factory(Provider<NobelApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public NobelRepositoryImpl get() {
    return newInstance(apiProvider.get());
  }

  public static NobelRepositoryImpl_Factory create(javax.inject.Provider<NobelApi> apiProvider) {
    return new NobelRepositoryImpl_Factory(Providers.asDaggerProvider(apiProvider));
  }

  public static NobelRepositoryImpl_Factory create(Provider<NobelApi> apiProvider) {
    return new NobelRepositoryImpl_Factory(apiProvider);
  }

  public static NobelRepositoryImpl newInstance(NobelApi api) {
    return new NobelRepositoryImpl(api);
  }
}
