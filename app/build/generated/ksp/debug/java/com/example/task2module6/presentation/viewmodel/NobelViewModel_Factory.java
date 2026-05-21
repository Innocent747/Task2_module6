package com.example.task2module6.presentation.viewmodel;

import com.example.task2module6.domain.usecase.GetPrizesUseCase;
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
public final class NobelViewModel_Factory implements Factory<NobelViewModel> {
  private final Provider<GetPrizesUseCase> getPrizesUseCaseProvider;

  public NobelViewModel_Factory(Provider<GetPrizesUseCase> getPrizesUseCaseProvider) {
    this.getPrizesUseCaseProvider = getPrizesUseCaseProvider;
  }

  @Override
  public NobelViewModel get() {
    return newInstance(getPrizesUseCaseProvider.get());
  }

  public static NobelViewModel_Factory create(
      javax.inject.Provider<GetPrizesUseCase> getPrizesUseCaseProvider) {
    return new NobelViewModel_Factory(Providers.asDaggerProvider(getPrizesUseCaseProvider));
  }

  public static NobelViewModel_Factory create(Provider<GetPrizesUseCase> getPrizesUseCaseProvider) {
    return new NobelViewModel_Factory(getPrizesUseCaseProvider);
  }

  public static NobelViewModel newInstance(GetPrizesUseCase getPrizesUseCase) {
    return new NobelViewModel(getPrizesUseCase);
  }
}
