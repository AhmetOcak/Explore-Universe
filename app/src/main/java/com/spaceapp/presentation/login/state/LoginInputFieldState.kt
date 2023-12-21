package com.spaceapp.presentation.login.state

sealed interface LoginInputFieldState {
    data class Error(val errorMessage: String) : LoginInputFieldState
    data object Nothing : LoginInputFieldState
}