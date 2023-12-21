package com.spaceapp.presentation.forgot_password.state

sealed interface ForgotPasswordState {
    data object Nothing : ForgotPasswordState
    data object Loading : ForgotPasswordState
    data object Success : ForgotPasswordState
    data class Error(val errorMessage: String?) : ForgotPasswordState
}