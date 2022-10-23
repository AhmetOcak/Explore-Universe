package com.spaceapp.domain.model

data class WeatherCondition(
    val weatherDescription: List<WeatherDescription>,
    val temp: Main
)