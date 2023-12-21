package com.spaceapp.presentation.signup.state

sealed interface SignUpState {
    data object Nothing : SignUpState
    data object Loading : SignUpState
    data object Success : SignUpState
    data class Error(val errorMessage: String) : SignUpState
}