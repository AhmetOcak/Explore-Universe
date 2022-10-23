package com.spaceapp.domain.model

data class Iss(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double,
    val velocity: Double,
    val visibility: String,
    val date: Long
)
