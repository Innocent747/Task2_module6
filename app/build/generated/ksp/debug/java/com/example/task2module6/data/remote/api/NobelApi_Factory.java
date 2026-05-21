package com.example.task2module6.data.remote.api;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import io.ktor.client.HttpClient;
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
public final class NobelApi_Factory implements Factory<NobelApi> {
  private final Provider<HttpClient> clientProvider;

  public NobelApi_Factory(Provider<HttpClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public NobelApi get() {
    return newInstance(clientProvider.get());
  }

  public static NobelApi_Factory create(javax.inject.Provider<HttpClient> clientProvider) {
    return new NobelApi_Factory(Providers.asDaggerProvider(clientProvider));
  }

  public static NobelApi_Factory create(Provider<HttpClient> clientProvider) {
    return new NobelApi_Factory(clientProvider);
  }

  public static NobelApi newInstance(HttpClient client) {
    return new NobelApi(client);
  }
}
