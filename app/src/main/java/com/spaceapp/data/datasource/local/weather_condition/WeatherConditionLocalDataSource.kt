package com.spaceapp.data.datasource.local.weather_condition

import com.spaceapp.data.datasource.local.weather_condition.db.entity.WeatherConditionEntity

interface WeatherConditionLocalDataSource {

    suspend fun addWeatherCondition(weatherConditionEntity: WeatherConditionEntity)

    suspend fun getWeatherCondition() : WeatherConditionEntity

    suspend fun updateWeatherCondition(weatherConditionEntity: WeatherConditionEntity)
}