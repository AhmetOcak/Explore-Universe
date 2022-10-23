package com.spaceapp.data.datasource.remote.where_is_the_iss.entity

import com.google.gson.annotations.SerializedName

data class IssDto(
    @SerializedName("latitude")
    val latitude: Double,

    @SerializedName("longitude")
    val longitude: Double,

    @SerializedName("altitude")
    val altitude: Double,

    @SerializedName("velocity")
    val velocity: Double,

    @SerializedName("visibility")
    val visibility: String,

    @SerializedName("timestamp")
    val date: Long,
)
