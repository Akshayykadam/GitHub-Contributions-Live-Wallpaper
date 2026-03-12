package com.githubwallpaper.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ContributionDao {
    @Query("SELECT * FROM contribution_days ORDER BY date ASC")
    fun getAllContributions(): Flow<List<ContributionDayEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertContributions(contributions: List<ContributionDayEntity>)

    @Query("DELETE FROM contribution_days")
    suspend fun clearContributions()
}
