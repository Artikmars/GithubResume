package com.artamonov.githubresume.main

import com.artamonov.githubresume.base.BaseViewModel
import com.artamonov.githubresume.main.interactor.MainInteractorImpl
import com.artamonov.githubresume.main.models.FetchMainStatus
import com.artamonov.githubresume.main.models.MainAction
import com.artamonov.githubresume.main.models.MainEvent
import com.artamonov.githubresume.main.models.MainViewState
import com.artamonov.githubresume.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import retrofit2.HttpException
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var interactor: MainInteractorImpl,
    private var compositeDisposable: CompositeDisposable,
    private var schedulerProvider: SchedulerProvider
) : BaseViewModel<MainViewState, MainAction, MainEvent>() {

    private var enteredUsername: String? = null

    init {
        viewState = MainViewState(FetchMainStatus.DefaultState, data = null)
    }

    override fun obtainEvent(viewEvent: MainEvent) {
        when (viewEvent) {
            is MainEvent.UserNameChanged -> {
                enteredUsername = viewEvent.username
                enteredUsername?.let { getUser() }
            }
            is MainEvent.SearchButtonClicked ->
                viewAction = if (viewState.data != null) {
                    MainAction.StartProfileActivity(viewState.data!!.login)
                } else {
                    MainAction.ShowSnackbar
                }
        }
    }

    private fun getUser() {
        compositeDisposable.add(interactor.getUserInfo(enteredUsername!!)
            .delay(300, TimeUnit.MILLISECONDS)
            .subscribeOn(schedulerProvider.getIOThreadScheduler())
            .observeOn(schedulerProvider.getMainThreadScheduler())
            .doOnSubscribe { viewAction = MainAction.ShowProgress }
            .doAfterTerminate { viewAction = MainAction.HideProgress }
            .subscribe(
                { userProfile ->
                    viewState = MainViewState(
                        fetchStatus = FetchMainStatus.UserExist,
                        data = userProfile
                    )
                }
                ,
                { e ->
                    if (e is HttpException) {
                        viewState = MainViewState(
                            fetchStatus = FetchMainStatus.NoFoundState,
                            data = null
                        )
                    }
                })
        )
    }

}
