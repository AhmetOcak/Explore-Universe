package com.spaceapp.presentation.login

sealed interface LoginState {
    object Nothing : LoginState
    object Loading : LoginState
    object Success : LoginState
    data class Error(val errorMessage: String) : LoginState
}