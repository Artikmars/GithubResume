package com.artamonov.githubresume.networking.api

import com.artamonov.githubresume.networking.models.GitHubProfileJson
import io.reactivex.Single

interface ApiHelper {
    fun getUser(username: String): Single<GitHubProfileJson>
}
