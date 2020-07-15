package com.artamonov.githubresume.main.models

sealed class MainAction {
    object ShowError : MainAction()
    object ShowProgress : MainAction()
    object HideProgress : MainAction()
    object ShowSnackbar : MainAction()
    data class StartProfileActivity(val username: String) : MainAction()
}
