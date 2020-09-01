package com.artamonov.githubresume.di

import com.artamonov.githubresume.utils.SchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Singleton
    @Provides
    internal fun provideSchedulerProvider(): SchedulerProvider = SchedulerProvider()


    @Singleton
    @Provides
    internal fun provideGson(): Gson = GsonBuilder().create()

}
