package com.spaceapp.data.datasource.remote.weather_condition

import com.spaceapp.data.datasource.remote.weather_condition.entity.WeatherConditionDto

interface WeatherConditionRemoteDataSource {

    suspend fun getCurrentWeather(latitude: Double, longitude: Double) : WeatherConditionDto
}