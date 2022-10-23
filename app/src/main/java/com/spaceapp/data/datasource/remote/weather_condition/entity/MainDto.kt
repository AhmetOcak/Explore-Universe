package com.spaceapp.data.datasource.remote.weather_condition.entity

import com.google.gson.annotations.SerializedName

data class MainDto(
    @SerializedName("temp")
    val temp: Double
)
