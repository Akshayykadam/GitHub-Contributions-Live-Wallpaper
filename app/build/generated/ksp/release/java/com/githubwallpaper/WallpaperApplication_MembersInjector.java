package com.githubwallpaper;

import androidx.hilt.work.HiltWorkerFactory;
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
public final class WallpaperApplication_MembersInjector implements MembersInjector<WallpaperApplication> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public WallpaperApplication_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<WallpaperApplication> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new WallpaperApplication_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(WallpaperApplication instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.githubwallpaper.WallpaperApplication.workerFactory")
  public static void injectWorkerFactory(WallpaperApplication instance,
      HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
