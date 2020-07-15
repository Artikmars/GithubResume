package com.artamonov.githubresume.main.interactor

import com.artamonov.githubresume.networking.models.GitHubProfileJson
import io.reactivex.Single

interface MainInteractor {
    fun getUserInfo(baseCurrency: String): Single<GitHubProfileJson>
}