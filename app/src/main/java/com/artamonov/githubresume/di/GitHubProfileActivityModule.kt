package com.artamonov.githubresume.di

import com.artamonov.githubresume.profile.interactor.GitHubProfileInteractorImpl
import com.artamonov.githubresume.profile.interactor.GitHubProfileInteractor
import dagger.Module
import dagger.Provides

@Module
class GitHubProfileActivityModule {

    @Provides
    internal fun provideGitHubProfileInteractor(interactor: GitHubProfileInteractorImpl): GitHubProfileInteractor = interactor

}
