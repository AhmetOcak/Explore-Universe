package com.spaceapp.presentation.signup.state

sealed interface SignUpState {
    object Nothing : SignUpState
    object Loading : SignUpState
    object Success : SignUpState
    data class Error(val errorMessage: String) : SignUpState
}