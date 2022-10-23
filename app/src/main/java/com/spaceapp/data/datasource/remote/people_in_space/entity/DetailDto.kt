package com.spaceapp.data.datasource.remote.people_in_space.entity

import com.google.gson.annotations.SerializedName

data class DetailDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("craft")
    val craft: String
)
