package com.githubwallpaper.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ContributionDayEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contributionDao(): ContributionDao
}
