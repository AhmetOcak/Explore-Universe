package com.spaceapp.data.datasource.remote.auth.entity

data class VerifyEmailDto(
    val userEmail: String,
    val action: Int
)
