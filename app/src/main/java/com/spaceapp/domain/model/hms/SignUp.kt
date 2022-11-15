package com.spaceapp.domain.model.hms

data class SignUp(
    val email: String,
    val password: String,
    val verifyCode: String
)
