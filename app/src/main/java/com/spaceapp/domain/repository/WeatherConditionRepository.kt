package com.spaceapp.domain.repository

import com.spaceapp.domain.model.weather_condition.WeatherCondition

interface WeatherConditionRepository {

    suspend fun getWeatherConditionFromNetwork(latitude: Double, longitude: Double): WeatherCondition

    suspend fun getWeatherConditionFromLocal(): WeatherCondition

    suspend fun addWeatherConditionToLocal(weatherCondition: WeatherCondition)

    suspend fun updateWeatherCondition(weatherCondition: WeatherCondition)
}