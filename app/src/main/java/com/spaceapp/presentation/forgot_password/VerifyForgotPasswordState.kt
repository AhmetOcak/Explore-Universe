package com.spaceapp.presentation.forgot_password

sealed interface VerifyForgotPasswordState {
    object Success : VerifyForgotPasswordState
    data class Error(val errorMessage: String?) : VerifyForgotPasswordState
    object Loading : VerifyForgotPasswordState
    object Nothing : VerifyForgotPasswordState
}