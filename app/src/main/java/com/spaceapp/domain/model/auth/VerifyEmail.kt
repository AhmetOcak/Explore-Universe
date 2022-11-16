package com.spaceapp.domain.model.auth

data class VerifyEmail(
    val userEmail: String,
    val action: Int
)
