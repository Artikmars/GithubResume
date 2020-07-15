package com.artamonov.githubresume.main.models

sealed class MainEvent {
    class UserNameChanged(val username: String) : MainEvent()
    object SearchButtonClicked : MainEvent()
}
