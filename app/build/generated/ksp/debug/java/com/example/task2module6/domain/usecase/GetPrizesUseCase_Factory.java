package com.example.task2module6.domain.usecase;

import com.example.task2module6.domain.repository.NobelRepository;
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
public final class GetPrizesUseCase_Factory implements Factory<GetPrizesUseCase> {
  private final Provider<NobelRepository> repositoryProvider;

  public GetPrizesUseCase_Factory(Provider<NobelRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetPrizesUseCase get() {
    return newInstance(repositoryProvider.get());
  }

  public static GetPrizesUseCase_Factory create(
      javax.inject.Provider<NobelRepository> repositoryProvider) {
    return new GetPrizesUseCase_Factory(Providers.asDaggerProvider(repositoryProvider));
  }

  public static GetPrizesUseCase_Factory create(Provider<NobelRepository> repositoryProvider) {
    return new GetPrizesUseCase_Factory(repositoryProvider);
  }

  public static GetPrizesUseCase newInstance(NobelRepository repository) {
    return new GetPrizesUseCase(repository);
  }
}
