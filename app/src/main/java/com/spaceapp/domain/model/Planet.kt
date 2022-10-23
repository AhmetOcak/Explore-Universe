package com.spaceapp.domain.model

data class Planet(
    val planetName: String,
    val planetDescription: String,
    val planetRadius: Double,
    val distanceFromSun: Long
)
