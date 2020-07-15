package com.artamonov.githubresume.networking.models

data class GitHubProfileJson (
    val avatar_url: String,
    val bio: String,
    val blog: String,
    val created_at: String,
    val location: String,
    val login: String,
    val name: String?,
    val public_repos: Int,
    val followers: Int,
    val following: Int,
    val message: String
)