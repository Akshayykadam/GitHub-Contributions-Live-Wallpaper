package com.githubwallpaper.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.preference.PreferenceManager
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.githubwallpaper.data.GithubRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class ContributionSyncWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val repository: GithubRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        val prefs = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        val username = prefs.getString("username", "") ?: ""

        if (username.isEmpty()) {
            return Result.failure()
        }

        return try {
            repository.syncContributions(username)
            Result.success()
        } catch (e: Exception) {
            e.printStackTrace()
            Result.retry()
        }
    }
}
