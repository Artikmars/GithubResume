package com.artamonov.githubresume.di

import androidx.lifecycle.ViewModelProvider
import com.artamonov.githubresume.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewFactoryModule {

    @Binds
    internal abstract fun provideModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}