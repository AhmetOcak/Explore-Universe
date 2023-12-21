package com.spaceapp.presentation.login.state

sealed interface LoginState {
    data object Nothing : LoginState
    data object Loading : LoginState
    data object Success : LoginState
    data class Error(val errorMessage: String) : LoginState
}