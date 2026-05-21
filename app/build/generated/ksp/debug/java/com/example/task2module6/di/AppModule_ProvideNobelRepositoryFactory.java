package com.example.task2module6.di;

import com.example.task2module6.data.repository.NobelRepositoryImpl;
import com.example.task2module6.domain.repository.NobelRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.Providers;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppModule_ProvideNobelRepositoryFactory implements Factory<NobelRepository> {
  private final Provider<NobelRepositoryImpl> implProvider;

  public AppModule_ProvideNobelRepositoryFactory(Provider<NobelRepositoryImpl> implProvider) {
    this.implProvider = implProvider;
  }

  @Override
  public NobelRepository get() {
    return provideNobelRepository(implProvider.get());
  }

  public static AppModule_ProvideNobelRepositoryFactory create(
      javax.inject.Provider<NobelRepositoryImpl> implProvider) {
    return new AppModule_ProvideNobelRepositoryFactory(Providers.asDaggerProvider(implProvider));
  }

  public static AppModule_ProvideNobelRepositoryFactory create(
      Provider<NobelRepositoryImpl> implProvider) {
    return new AppModule_ProvideNobelRepositoryFactory(implProvider);
  }

  public static NobelRepository provideNobelRepository(NobelRepositoryImpl impl) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideNobelRepository(impl));
  }
}
