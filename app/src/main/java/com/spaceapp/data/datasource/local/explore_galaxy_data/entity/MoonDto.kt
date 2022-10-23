package com.spaceapp.data.datasource.local.explore_galaxy_data.entity

import com.google.gson.annotations.SerializedName

data class MoonDto(
    @SerializedName("name")
    val moonName: String,

    @SerializedName("description")
    val moonDescription: String,

    @SerializedName("radius")
    val moonRadius: Double,

    @SerializedName("distance_from_sun")
    val distanceFromSun: Long
)
