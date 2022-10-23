package com.spaceapp.data.datasource.local.explore_galaxy_data.entity

import com.google.gson.annotations.SerializedName

data class MeteorDto(
    @SerializedName("name")
    val meteorName: String,

    @SerializedName("description")
    val meteorDescription: String,

    @SerializedName("velocity")
    val meteorVelocity: Int
)