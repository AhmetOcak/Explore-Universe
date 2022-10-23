package com.spaceapp.presentation.signup

sealed interface SignUpInputFieldState {
    data class Error(val errorMessage: String) : SignUpInputFieldState
    object Nothing : SignUpInputFieldState
}