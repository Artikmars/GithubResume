package com.artamonov.githubresume

import androidx.lifecycle.ViewModelProvider
import com.artamonov.githubresume.main.MainViewModel
import com.artamonov.githubresume.main.interactor.MainInteractor
import com.artamonov.githubresume.main.interactor.MainInteractorImpl
import com.artamonov.githubresume.networking.models.GitHubProfileJson
import com.artamonov.githubresume.networking.models.GithubProfile
import com.artamonov.githubresume.profile.GitHubProfileViewModel
import com.artamonov.githubresume.utils.SchedulerProvider
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.mockk
import io.mockk.unmockkAll
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.After
import org.junit.Before
import org.junit.ClassRule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HomeFeedPresenterTest {

    private var schedulerProvider: SchedulerProvider = mockk()

    private var disposable: CompositeDisposable = mockk()

    private var interactor: MainInteractorImpl = mockk()

    private var viewModel: GitHubProfileViewModel = mockk()

    @Before
    fun setUp() {
        clearAllMocks()
        MockitoAnnotations.initMocks(this)
        every { schedulerProvider.getIOThreadScheduler() } returns Schedulers.trampoline()
        every { schedulerProvider.getMainThreadScheduler() } returns Schedulers.trampoline()
        viewModel = GitHubProfileViewModel(interactor, disposable, schedulerProvider)
    }

    @Test
    fun `mapProfile() returns correct user object`() {
        val jsonProfile = GitHubProfileJson("", "", "", "2014-5-05", "", "Andrey",
        null, 4, 5,6, "")
        val mappedUser = GithubProfile("", "", "2014", "", "Andrey",
            4, 5, 6, "")
        `when`(viewModel.mapProfile(jsonProfile)).thenReturn(mappedUser)
        assertEquals(mappedUser.name, jsonProfile.login)
        assertEquals(mappedUser.created_at, jsonProfile.created_at.take(4))
        assertEquals(mappedUser.name, jsonProfile.login)
    }

    @After
    fun afterTests() {
        unmockkAll()
    }
}