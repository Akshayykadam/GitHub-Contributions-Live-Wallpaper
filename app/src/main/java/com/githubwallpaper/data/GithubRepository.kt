package com.githubwallpaper.data

import com.githubwallpaper.network.GitHubApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GithubRepository @Inject constructor(
    private val api: GitHubApi,
    private val dao: ContributionDao
) {
    val contributions: Flow<List<ContributionDayEntity>> = dao.getAllContributions()

    suspend fun syncContributions(username: String) {
        val responseBody = api.fetchContributionsHtml(username)
        val htmlContent = responseBody.string()
        
        // Match td tags specifically
        val regex = "<td[^>]*data-date=\"([^\"]+)\"[^>]*data-level=\"([0-4])\"[^>]*>".toRegex()
        val matches = regex.findAll(htmlContent)
        
        val days = matches.map { matchResult ->
            val date = matchResult.groupValues[1]
            val levelStr = matchResult.groupValues[2]
            val level = levelStr.toIntOrNull() ?: 0
            
            // Generate a synthetic contribution count based on level
            val fakeCount = when(level) {
                0 -> 0
                1 -> 2
                2 -> 5
                3 -> 8
                4 -> 12
                else -> 0
            }
            
            ContributionDayEntity(
                date = date,
                contributionCount = fakeCount,
                color = "" // We don't need the exact hex color from API, wallpaper renderer maps count to its own theme
            )
        }.toList()
        
        if (days.isNotEmpty()) {
            dao.insertContributions(days)
        }
    }
}
