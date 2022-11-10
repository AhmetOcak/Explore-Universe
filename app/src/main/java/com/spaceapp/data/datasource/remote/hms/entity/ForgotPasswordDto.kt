package com.spaceapp.data.datasource.remote.hms.entity

data class ForgotPasswordDto(
    val email: String,
    val newPassword: String,
    val verifyCode: String
)
