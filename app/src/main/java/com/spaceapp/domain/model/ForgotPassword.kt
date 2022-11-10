package com.spaceapp.domain.model

data class ForgotPassword(
    val email: String,
    val newPassword: String,
    val verifyCode: String
)
