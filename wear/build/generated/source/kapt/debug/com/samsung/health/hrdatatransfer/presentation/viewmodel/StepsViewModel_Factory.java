package com.samsung.health.hrdatatransfer.presentation.viewmodel;

import android.app.Application;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "KotlinInternalInJava"
})
public final class StepsViewModel_Factory implements Factory<StepsViewModel> {
  private final Provider<Application> applicationProvider;

  public StepsViewModel_Factory(Provider<Application> applicationProvider) {
    this.applicationProvider = applicationProvider;
  }

  @Override
  public StepsViewModel get() {
    return newInstance(applicationProvider.get());
  }

  public static StepsViewModel_Factory create(Provider<Application> applicationProvider) {
    return new StepsViewModel_Factory(applicationProvider);
  }

  public static StepsViewModel newInstance(Application application) {
    return new StepsViewModel(application);
  }
}
