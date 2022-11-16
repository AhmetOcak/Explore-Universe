package com.spaceapp.domain.model.auth

data class SignUp(
    val email: String,
    val password: String,
    val verifyCode: String
)
