package com.spaceapp.domain.model.weather_condition

data class WeatherCondition(
    val weatherDescription: List<WeatherDescription>,
    val temp: Main
)