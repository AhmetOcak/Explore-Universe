package com.spaceapp.data.datasource.remote.weather_condition.api

import com.spaceapp.BuildConfig
import com.spaceapp.data.datasource.remote.weather_condition.entity.WeatherConditionDto
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrentWeatherApi {

    @GET("/data/2.5/weather")
    suspend fun getCurrentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String = BuildConfig.WEATHER_API_KEY,
        @Query("units") units: String = "metric",
    ): WeatherConditionDto
}