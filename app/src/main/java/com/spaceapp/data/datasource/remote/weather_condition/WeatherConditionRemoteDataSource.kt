package com.spaceapp.data.datasource.remote.weather_condition

import com.spaceapp.data.datasource.remote.weather_condition.api.CurrentWeatherApi
import javax.inject.Inject

class WeatherConditionRemoteDataSource @Inject constructor(private val api: CurrentWeatherApi) {

    suspend fun getCurrentWeather(latitude: Double, longitude: Double) =
        api.getCurrentWeather(latitude = latitude, longitude = longitude)
}