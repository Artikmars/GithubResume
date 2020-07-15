package com.artamonov.githubresume.networking.api

import com.artamonov.githubresume.networking.models.GitHubProfileJson
import io.reactivex.Single
import javax.inject.Inject

class AppApiHelper @Inject constructor(private val api: API) : ApiHelper {

    override fun getUser(username: String): Single<GitHubProfileJson> {
        return api.getUserInfo(username)
    }
}
