package com.spaceapp.domain.model.hms

data class ForgotPassword(
    val email: String,
    val newPassword: String,
    val verifyCode: String
)
