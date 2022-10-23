package com.spaceapp.data.datasource.remote.weather_condition.entity

import com.google.gson.annotations.SerializedName

data class WeatherDescriptionDto(
    @SerializedName("main")
    val mainDescription: String,

    @SerializedName("description")
    val description: String
)
