package com.githubwallpaper.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contribution_days")
data class ContributionDayEntity(
    @PrimaryKey
    val date: String,
    val contributionCount: Int,
    val color: String
)
