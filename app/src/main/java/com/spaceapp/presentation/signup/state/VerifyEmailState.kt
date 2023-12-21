package com.spaceapp.presentation.signup.state

sealed interface VerifyEmailState {
    data object Success : VerifyEmailState
    data class Error(val errorMessage: String?) : VerifyEmailState
    data object Loading : VerifyEmailState
    data object Nothing : VerifyEmailState
}