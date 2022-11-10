package com.spaceapp.presentation.forgot_password

sealed interface ForgotPasswordInputFieldState{
    data class Error(val errorMessage: String) : ForgotPasswordInputFieldState
    object Nothing : ForgotPasswordInputFieldState
}