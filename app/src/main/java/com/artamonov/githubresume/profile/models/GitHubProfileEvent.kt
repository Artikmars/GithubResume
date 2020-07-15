package com.artamonov.githubresume.profile.models

import android.content.Intent

sealed class GitHubProfileEvent {
    data class HandleIntent(val intent: Intent) : GitHubProfileEvent()
}
