package com.artamonov.githubresume.profile.interactor

import com.artamonov.githubresume.networking.api.ApiHelper
import com.artamonov.githubresume.networking.models.GitHubProfileJson
import io.reactivex.Single
import javax.inject.Inject

class GitHubProfileInteractorImpl @Inject
constructor(
    private val apiHelper: ApiHelper
) : GitHubProfileInteractor {

    override fun getUserInfo(userProfile: String): Single<GitHubProfileJson> {
        return apiHelper.getUser(userProfile)

    }
}