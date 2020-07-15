package com.artamonov.githubresume.di

import com.artamonov.githubresume.main.interactor.MainInteractor
import com.artamonov.githubresume.main.interactor.MainInteractorImpl
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

    @Provides
    internal fun provideMainInteractor(interactor: MainInteractorImpl): MainInteractor = interactor
}
