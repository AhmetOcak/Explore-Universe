package com.spaceapp.domain.model.auth

data class ForgotPassword(
    val email: String,
    val newPassword: String,
    val verifyCode: String
)
