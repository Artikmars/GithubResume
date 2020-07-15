package com.artamonov.githubresume.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SchedulerProvider {

    fun getIOThreadScheduler() = Schedulers.io()

    fun getMainThreadScheduler(): Scheduler = AndroidSchedulers.mainThread()
}