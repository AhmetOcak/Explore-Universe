package com.spaceapp.data.datasource.local.explore_galaxy_data.entity

import com.google.gson.annotations.SerializedName

data class PlanetDto(
    @SerializedName("name")
    val planetName: String,

    @SerializedName("description")
    val planetDescription: String,

    @SerializedName("radius")
    val planetRadius: Double,

    @SerializedName("distance_from_sun")
    val distanceFromSun: Long
)
