package com.artamonov.githubresume.profile.interactor

import com.artamonov.githubresume.networking.models.GitHubProfileJson
import io.reactivex.Single

interface GitHubProfileInteractor {
    fun getUserInfo(userProfile: String): Single<GitHubProfileJson>
}