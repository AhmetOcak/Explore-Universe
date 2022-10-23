package com.spaceapp.presentation.login

sealed interface LoginInputFieldState {
    data class Error(val errorMessage: String) : LoginInputFieldState
    object Nothing : LoginInputFieldState
}