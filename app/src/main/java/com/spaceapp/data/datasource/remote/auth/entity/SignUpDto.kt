package com.spaceapp.data.datasource.remote.auth.entity

data class SignUpDto(
    val email: String,
    val password: String,
    val verifyCode: String
)