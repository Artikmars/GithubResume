package com.artamonov.githubresume.profile.models

import com.artamonov.githubresume.networking.models.GithubProfile

sealed class GitHubProfileAction {
    data class PopulateView(val profile: GithubProfile) : GitHubProfileAction()
}
