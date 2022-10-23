package com.spaceapp.presentation.signup

sealed interface VerifyEmailState {
    object Success : VerifyEmailState
    data class Error(val errorMessage: String?) : VerifyEmailState
    object Loading : VerifyEmailState
    object Nothing : VerifyEmailState
}