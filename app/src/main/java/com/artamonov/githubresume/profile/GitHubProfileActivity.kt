package com.artamonov.githubresume.profile

import android.os.Bundle
import android.text.util.Linkify
import android.view.View
import android.view.View.VISIBLE
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.artamonov.githubresume.R
import com.artamonov.githubresume.base.BaseActivity
import com.artamonov.githubresume.profile.models.GitHubProfileAction
import com.artamonov.githubresume.profile.models.GitHubProfileEvent
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_git_hub_profile.*
import javax.inject.Inject

class GitHubProfileActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: GitHubProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_git_hub_profile)
        viewModel = ViewModelProvider(this, viewModelFactory)[GitHubProfileViewModel::class.java]
        viewModel.viewEffects().observe(this, Observer { bindViewAction(it) })
        viewModel.obtainEvent(GitHubProfileEvent.HandleIntent(intent))
    }

    private fun bindViewAction(viewAction: GitHubProfileAction) {
        when (viewAction) {
            is GitHubProfileAction.PopulateView -> {
                github_name.text = viewAction.profile.name

                github_description.text = getString(
                    R.string.github_profile_description,
                    viewAction.profile.created_at, viewAction.profile.name,
                    viewAction.profile.location, viewAction.profile.public_repos,
                    viewAction.profile.followers
                )

                viewAction.profile.blog?.let {
                    github_blog.visibility = VISIBLE
                    github_blog.text = it
                    Linkify.addLinks(github_blog, Linkify.WEB_URLS)
                }

                Glide
                    .with(this)
                    .load(viewAction.profile.avatar_url)
                    .apply(RequestOptions.circleCropTransform())
                    .into(user_edit_add_image)
            }

        }
    }

    override fun hideProgress() {
        github_profile_progress_bar.visibility = View.GONE
    }

    override fun showProgress() {
        github_profile_progress_bar.visibility = VISIBLE
    }

}
