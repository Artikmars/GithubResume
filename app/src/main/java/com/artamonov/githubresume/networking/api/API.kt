package com.artamonov.githubresume.networking.api

import com.artamonov.githubresume.networking.models.GitHubProfileJson
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Url

interface API {

    @GET
    fun getUserInfo(@Url username: String): Single<GitHubProfileJson>
}