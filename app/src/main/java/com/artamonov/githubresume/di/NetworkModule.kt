package com.artamonov.githubresume.di

import com.artamonov.githubresume.networking.api.API
import com.artamonov.githubresume.networking.api.ApiHelper
import com.artamonov.githubresume.networking.api.AppApiHelper
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideApiHelper(appApiHelper: AppApiHelper): ApiHelper = appApiHelper

    @Provides
    @Singleton
    fun providesNetworkService(@Named("DefaultRetrofit") retrofit: Retrofit): API = retrofit.create(
        API::class.java
    )

    @Provides
    @Singleton
    @Named("DefaultRetrofit")
    internal fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return createRetrofitInstance(okHttpClient, gson)
    }

    private fun createRetrofitInstance(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.github.com/users/")
            .client(okHttpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun provideOkHttp(): OkHttpClient {
        return createOkHttpClient()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
        return okHttpClientBuilder.build()
    }
}
