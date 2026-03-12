package com.githubwallpaper.di;

import com.githubwallpaper.data.AppDatabase;
import com.githubwallpaper.data.ContributionDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideDaoFactory implements Factory<ContributionDao> {
  private final Provider<AppDatabase> databaseProvider;

  public DatabaseModule_ProvideDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public ContributionDao get() {
    return provideDao(databaseProvider.get());
  }

  public static DatabaseModule_ProvideDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new DatabaseModule_ProvideDaoFactory(databaseProvider);
  }

  public static ContributionDao provideDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDao(database));
  }
}
