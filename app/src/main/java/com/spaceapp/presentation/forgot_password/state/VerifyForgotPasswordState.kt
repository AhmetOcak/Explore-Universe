package com.spaceapp.presentation.forgot_password.state

sealed interface VerifyForgotPasswordState {
    data object Success : VerifyForgotPasswordState
    data class Error(val errorMessage: String?) : VerifyForgotPasswordState
    data object Loading : VerifyForgotPasswordState
    data object Nothing : VerifyForgotPasswordState
}