package com.spaceapp.presentation.signup

sealed interface SignUpState {
    object Nothing : SignUpState
    object Loading : SignUpState
    object Success : SignUpState
    data class Error(val errorMessage: String) : SignUpState
}