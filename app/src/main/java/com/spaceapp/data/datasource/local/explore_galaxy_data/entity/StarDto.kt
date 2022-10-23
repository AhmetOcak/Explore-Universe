package com.spaceapp.data.datasource.local.explore_galaxy_data.entity

import com.google.gson.annotations.SerializedName

data class StarDto(
    @SerializedName("name")
    val starName: String,

    @SerializedName("description")
    val starDescription: String
)
