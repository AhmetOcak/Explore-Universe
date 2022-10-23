package com.spaceapp.data.datasource.local.explore_galaxy_data.entity

import com.google.gson.annotations.SerializedName

data class SpaceObjectDto(
    @SerializedName("all")
    val spaceObjects: List<ObjectDto>
)
