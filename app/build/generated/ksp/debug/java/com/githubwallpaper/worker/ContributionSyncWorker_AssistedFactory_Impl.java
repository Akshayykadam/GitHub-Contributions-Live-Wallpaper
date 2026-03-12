package com.githubwallpaper.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "cast"
})
public final class ContributionSyncWorker_AssistedFactory_Impl implements ContributionSyncWorker_AssistedFactory {
  private final ContributionSyncWorker_Factory delegateFactory;

  ContributionSyncWorker_AssistedFactory_Impl(ContributionSyncWorker_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public ContributionSyncWorker create(Context p0, WorkerParameters p1) {
    return delegateFactory.get(p0, p1);
  }

  public static Provider<ContributionSyncWorker_AssistedFactory> create(
      ContributionSyncWorker_Factory delegateFactory) {
    return InstanceFactory.create(new ContributionSyncWorker_AssistedFactory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<ContributionSyncWorker_AssistedFactory> createFactoryProvider(
      ContributionSyncWorker_Factory delegateFactory) {
    return InstanceFactory.create(new ContributionSyncWorker_AssistedFactory_Impl(delegateFactory));
  }
}
