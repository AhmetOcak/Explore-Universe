package com.spaceapp.domain.model

data class SignUp(
    val email: String,
    val password: String,
    val verifyCode: String
)
