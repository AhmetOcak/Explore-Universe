package com.spaceapp.data.datasource.remote.weather_condition.entity

import com.google.gson.annotations.SerializedName

data class WeatherConditionDto(
    @SerializedName("weather")
    val weatherDescription: List<WeatherDescriptionDto>,

    @SerializedName("main")
    val temp: MainDto
)
