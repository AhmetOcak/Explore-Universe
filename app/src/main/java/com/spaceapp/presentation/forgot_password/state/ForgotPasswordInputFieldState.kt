package com.spaceapp.presentation.forgot_password.state

sealed interface ForgotPasswordInputFieldState{
    data class Error(val errorMessage: String) : ForgotPasswordInputFieldState
    data object Nothing : ForgotPasswordInputFieldState
}