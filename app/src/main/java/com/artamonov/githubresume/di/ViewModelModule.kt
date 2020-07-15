package com.artamonov.githubresume.di

import androidx.lifecycle.ViewModel
import com.artamonov.githubresume.main.MainViewModel
import com.artamonov.githubresume.profile.GitHubProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    internal abstract fun provideMMainViewModel(viewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(GitHubProfileViewModel::class)
    internal abstract fun provideGitHubProfileViewModel(viewModel: GitHubProfileViewModel): ViewModel



}
