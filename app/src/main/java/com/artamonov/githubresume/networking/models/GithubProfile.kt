package com.artamonov.githubresume.networking.models

data class GithubProfile (
    val avatar_url: String?,
    val blog: String?,
    val created_at: String?,
    val location: String?,
    val name: String?,
    val public_repos: Int?,
    val followers: Int?,
    val following: Int?,
    val message: String?
)