package com.artamonov.githubresume.profile

import com.artamonov.githubresume.base.BaseViewModel
import com.artamonov.githubresume.main.MainActivity.Companion.USERNAME
import com.artamonov.githubresume.main.interactor.MainInteractorImpl
import com.artamonov.githubresume.networking.models.GitHubProfileJson
import com.artamonov.githubresume.networking.models.GithubProfile
import com.artamonov.githubresume.profile.models.FetchGitHubProfileStatus
import com.artamonov.githubresume.profile.models.GitHubProfileAction
import com.artamonov.githubresume.profile.models.GitHubProfileEvent
import com.artamonov.githubresume.profile.models.GitHubProfileViewState
import com.artamonov.githubresume.utils.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class GitHubProfileViewModel @Inject constructor(
    var interactor: MainInteractorImpl,
    private var compositeDisposable: CompositeDisposable,
    private var schedulerProvider: SchedulerProvider
) : BaseViewModel<GitHubProfileViewState, GitHubProfileAction, GitHubProfileEvent>() {

    private var username: String? = null

    init {
        viewState = GitHubProfileViewState(FetchGitHubProfileStatus.DefaultState, data = null)
    }

    override fun obtainEvent(viewEvent: GitHubProfileEvent) {
        when (viewEvent) {
            is GitHubProfileEvent.HandleIntent -> {
                username = viewEvent.intent.getStringExtra(USERNAME)
                username?.let { getUser() }
            }

        }
    }

    private fun getUser() {
        compositeDisposable.add(interactor.getUserInfo(username!!)
            .map { user -> mapProfile(user) }
            .subscribeOn(schedulerProvider.getIOThreadScheduler())
            .observeOn(schedulerProvider.getMainThreadScheduler())
            .doOnSubscribe {
                viewState = viewState.copy(fetchStatus = FetchGitHubProfileStatus.ShowProgress)
            }
            .doAfterTerminate {
                viewState = viewState.copy(fetchStatus = FetchGitHubProfileStatus.HideProgress)
            }
            .subscribe({ userProfile ->
                viewState = viewState.copy(
                    fetchStatus = FetchGitHubProfileStatus.Loaded,
                    data = userProfile
                )
                viewAction = GitHubProfileAction.PopulateView(userProfile)
            },
                { e ->
                    println(e)
                })
        )
    }

    fun mapProfile(user: GitHubProfileJson): GithubProfile {
        return GithubProfile(
            user.avatar_url, user.blog, user.created_at.take(4), user.location,
            user.name ?: user.login, user.public_repos, user.followers, user.following, user.message
        )
    }

}
