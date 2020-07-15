package com.artamonov.githubresume.profile.models

import com.artamonov.githubresume.networking.models.GithubProfile

data class GitHubProfileViewState(
    val fetchStatus: FetchGitHubProfileStatus,
    val data: GithubProfile?
)

sealed class FetchGitHubProfileStatus {
    object DefaultState : FetchGitHubProfileStatus()
    object Loaded : FetchGitHubProfileStatus()
    object ShowProgress : FetchGitHubProfileStatus()
    object HideProgress : FetchGitHubProfileStatus()
}
