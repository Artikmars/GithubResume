package com.artamonov.githubresume.di

import com.artamonov.githubresume.networking.api.ApiHelper
import com.artamonov.githubresume.networking.api.AppApiHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
abstract class GsonModule {

    @Singleton
    @Binds
    abstract fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper
}


