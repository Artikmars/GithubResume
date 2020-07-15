package com.artamonov.githubresume.di

import android.app.Application
import com.artamonov.githubresume.MyApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    (AndroidInjectionModule::class),
    (AppModule::class),
    (ActivityBuilder::class),
    (NetworkModule::class),
    (ViewModelModule::class),
    (ViewFactoryModule::class)
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(app: MyApplication)
}