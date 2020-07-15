package com.artamonov.githubresume.main.models

import com.artamonov.githubresume.networking.models.GitHubProfileJson

data class MainViewState(
    val fetchStatus: FetchMainStatus,
    val data: GitHubProfileJson?
)

sealed class FetchMainStatus {
    object DefaultState : FetchMainStatus()
    object UserExist: FetchMainStatus()
    object NoFoundState : FetchMainStatus()
}
