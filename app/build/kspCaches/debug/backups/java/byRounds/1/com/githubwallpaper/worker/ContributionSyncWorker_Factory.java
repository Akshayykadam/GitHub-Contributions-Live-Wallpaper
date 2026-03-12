package com.githubwallpaper.worker;

import android.content.Context;
import androidx.work.WorkerParameters;
import com.githubwallpaper.data.GithubRepository;
import dagger.internal.DaggerGenerated;
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
    "KotlinInternalInJava",
    "cast"
})
public final class ContributionSyncWorker_Factory {
  private final Provider<GithubRepository> repositoryProvider;

  public ContributionSyncWorker_Factory(Provider<GithubRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public ContributionSyncWorker get(Context context, WorkerParameters workerParams) {
    return newInstance(context, workerParams, repositoryProvider.get());
  }

  public static ContributionSyncWorker_Factory create(
      Provider<GithubRepository> repositoryProvider) {
    return new ContributionSyncWorker_Factory(repositoryProvider);
  }

  public static ContributionSyncWorker newInstance(Context context, WorkerParameters workerParams,
      GithubRepository repository) {
    return new ContributionSyncWorker(context, workerParams, repository);
  }
}
