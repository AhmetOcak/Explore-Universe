package com.spaceapp.data.datasource.local.explore_galaxy_data.entity

import com.google.gson.annotations.SerializedName

data class CometDto(
    @SerializedName("name")
    val cometName: String,

    @SerializedName("description")
    val cometDescription: String,

    @SerializedName("radius")
    val cometRadius: Double
)
