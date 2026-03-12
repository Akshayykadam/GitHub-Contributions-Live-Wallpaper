package com.githubwallpaper.ui;

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
public final class SettingsActivity_MembersInjector implements MembersInjector<SettingsActivity> {
  private final Provider<GithubRepository> repositoryProvider;

  public SettingsActivity_MembersInjector(Provider<GithubRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public static MembersInjector<SettingsActivity> create(
      Provider<GithubRepository> repositoryProvider) {
    return new SettingsActivity_MembersInjector(repositoryProvider);
  }

  @Override
  public void injectMembers(SettingsActivity instance) {
    injectRepository(instance, repositoryProvider.get());
  }

  @InjectedFieldSignature("com.githubwallpaper.ui.SettingsActivity.repository")
  public static void injectRepository(SettingsActivity instance, GithubRepository repository) {
    instance.repository = repository;
  }
}
