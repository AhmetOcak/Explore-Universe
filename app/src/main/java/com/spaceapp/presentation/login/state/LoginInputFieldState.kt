package com.spaceapp.presentation.login.state

sealed interface LoginInputFieldState {
    data class Error(val errorMessage: String) : LoginInputFieldState
    object Nothing : LoginInputFieldState
}