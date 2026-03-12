package com.githubwallpaper.data;

import com.githubwallpaper.network.GitHubApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
    "cast"
})
public final class GithubRepository_Factory implements Factory<GithubRepository> {
  private final Provider<GitHubApi> apiProvider;

  private final Provider<ContributionDao> daoProvider;

  public GithubRepository_Factory(Provider<GitHubApi> apiProvider,
      Provider<ContributionDao> daoProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public GithubRepository get() {
    return newInstance(apiProvider.get(), daoProvider.get());
  }

  public static GithubRepository_Factory create(Provider<GitHubApi> apiProvider,
      Provider<ContributionDao> daoProvider) {
    return new GithubRepository_Factory(apiProvider, daoProvider);
  }

  public static GithubRepository newInstance(GitHubApi api, ContributionDao dao) {
    return new GithubRepository(api, dao);
  }
}
