package com.artamonov.githubresume.di

import com.artamonov.githubresume.main.interactor.MainInteractor
import com.artamonov.githubresume.main.interactor.MainInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
abstract class MainActivityModule {

    @Singleton
    @Binds
    abstract fun provideMainInteractor(interactor: MainInteractorImpl): MainInteractor
}
