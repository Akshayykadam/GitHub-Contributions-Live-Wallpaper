package com.githubwallpaper.di

import android.content.Context
import androidx.room.Room
import com.githubwallpaper.data.AppDatabase
import com.githubwallpaper.data.ContributionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "github_wallpaper_db"
        )
        .fallbackToDestructiveMigration()
        .build()
    }

    @Provides
    fun provideDao(database: AppDatabase): ContributionDao {
        return database.contributionDao()
    }
}
