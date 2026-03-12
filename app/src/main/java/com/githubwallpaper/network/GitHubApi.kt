package com.githubwallpaper.network

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubApi {
    @GET("users/{username}/contributions")
    suspend fun fetchContributionsHtml(
        @Path("username") username: String
    ): ResponseBody
}
