package com.artamonov.githubresume.di

import com.artamonov.githubresume.main.MainActivity
import com.artamonov.githubresume.profile.GitHubProfileActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(MainActivityModule::class)])
    abstract fun bindGitHubProfileActivity(): GitHubProfileActivity
}