package com.artamonov.githubresume
import android.app.Application
import com.artamonov.githubresume.di.AppComponent
import com.artamonov.githubresume.di.DaggerAppComponent
import com.facebook.stetho.Stetho
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {

    @Inject
    internal lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
            .application(this)
            .build()
        component.inject(this)

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }

    override fun androidInjector() = dispatchingAndroidInjector
}
