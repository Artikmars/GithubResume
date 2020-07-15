package com.artamonov.githubresume.main.interactor

import com.artamonov.githubresume.networking.api.ApiHelper
import com.artamonov.githubresume.networking.models.GitHubProfileJson
import io.reactivex.Single
import javax.inject.Inject

class MainInteractorImpl @Inject
constructor(
    private val apiHelper: ApiHelper
) : MainInteractor {

    override fun getUserInfo(nickname: String): Single<GitHubProfileJson> {
        return apiHelper.getUser(nickname)
    }
}