package com.githubwallpaper.wallpaper;

import com.githubwallpaper.data.GithubRepository;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class GitHubWallpaperService_MembersInjector implements MembersInjector<GitHubWallpaperService> {
  private final Provider<GithubRepository> repositoryProvider;

  public GitHubWallpaperService_MembersInjector(Provider<GithubRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public static MembersInjector<GitHubWallpaperService> create(
      Provider<GithubRepository> repositoryProvider) {
    return new GitHubWallpaperService_MembersInjector(repositoryProvider);
  }

  @Override
  public void injectMembers(GitHubWallpaperService instance) {
    injectRepository(instance, repositoryProvider.get());
  }

  @InjectedFieldSignature("com.githubwallpaper.wallpaper.GitHubWallpaperService.repository")
  public static void injectRepository(GitHubWallpaperService instance,
      GithubRepository repository) {
    instance.repository = repository;
  }
}
