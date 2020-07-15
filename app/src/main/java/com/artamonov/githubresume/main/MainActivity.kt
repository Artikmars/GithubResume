package com.artamonov.githubresume.main

import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artamonov.githubresume.R
import com.artamonov.githubresume.base.BaseActivity
import com.artamonov.githubresume.main.models.FetchMainStatus
import com.artamonov.githubresume.main.models.MainAction
import com.artamonov.githubresume.main.models.MainEvent
import com.artamonov.githubresume.main.models.MainViewState
import com.artamonov.githubresume.utils.PostTextChangeWatcher
import com.artamonov.githubresume.profile.GitHubProfileActivity
import com.artamonov.githubresume.utils.NetworkConnectivityHelper
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        profile_name_edit.addTextChangedListener(PostTextChangeWatcher {
            viewModel.obtainEvent(MainEvent.UserNameChanged(it))
        })

        search_btn.setOnClickListener {
            if (!NetworkConnectivityHelper.isConnected(this)) {
                showSnackBar(R.string.main_internet_is_missing_error)
                return@setOnClickListener
            }
            viewModel.obtainEvent(MainEvent.SearchButtonClicked)
        }

        viewModel.viewStates().observe(this, Observer { bindViewState(it) })
        viewModel.viewEffects().observe(this, Observer { bindViewAction(it) })

    }

    override fun hideProgress() {
        main_progress_bar.visibility = GONE
    }

    override fun showProgress() {
        main_progress_bar.visibility = VISIBLE
    }

    private fun bindViewState(viewState: MainViewState) {
        when (viewState.fetchStatus) {
            FetchMainStatus.NoFoundState -> showError(true)
            FetchMainStatus.UserExist -> showError(false)
            }
        }

    private fun bindViewAction(viewAction: MainAction) {
        when (viewAction) {
            is MainAction.StartProfileActivity -> {
                val intent = Intent(this, GitHubProfileActivity::class.java)
                intent.putExtra(USERNAME, viewAction.username)
                startActivity(intent)
            }
            is MainAction.ShowError -> showError(true)
            is MainAction.ShowProgress -> showProgress()
            is MainAction.HideProgress -> hideProgress()
            is MainAction.ShowSnackbar -> showSnackBar(R.string.main_user_does_not_exist_error)
        }
    }

    private fun showSnackBar(errorMessage: Int) {
        Snackbar.make(findViewById(android.R.id.content),
            getString(errorMessage), Snackbar.LENGTH_SHORT).show()
    }

     private fun showError(state: Boolean) {
        if (state) {
            profile_name_edit_layout.error = resources.getString(R.string.main_user_does_not_exist_error)
            profile_name_edit_layout.isErrorEnabled = true
        } else {
            profile_name_edit_layout.error = null
            profile_name_edit_layout.isErrorEnabled = false
        }
    }

    companion object {
        const val USERNAME = "USERNAME"
    }

}