package com.spaceapp.data.repository

import com.spaceapp.data.datasource.local.db.WeatherConditionLocalDataSource
import com.spaceapp.data.datasource.remote.weather_condition.WeatherConditionRemoteDataSource
import com.spaceapp.data.mappers.toWeatherCondition
import com.spaceapp.data.mappers.toWeatherConditionEntity
import com.spaceapp.domain.model.weather_condition.WeatherCondition
import com.spaceapp.domain.repository.WeatherConditionRepository
import javax.inject.Inject

class WeatherConditionRepositoryImpl @Inject constructor(
    private val localDataSource: WeatherConditionLocalDataSource,
    private val remoteDataSource: WeatherConditionRemoteDataSource
) : WeatherConditionRepository {

    override suspend fun getWeatherConditionFromNetwork(
        latitude: Double,
        longitude: Double
    ): WeatherCondition =
        remoteDataSource.getCurrentWeather(latitude = latitude, longitude = longitude).toWeatherCondition()

    override suspend fun getWeatherConditionFromLocal(): WeatherCondition =
        localDataSource.getWeatherCondition().toWeatherCondition()

    override suspend fun addWeatherConditionToLocal(weatherCondition: WeatherCondition) =
        localDataSource.addWeatherCondition(weatherConditionEntity = weatherCondition.toWeatherConditionEntity())

    override suspend fun updateWeatherCondition(weatherCondition: WeatherCondition) =
        localDataSource.updateWeatherCondition(weatherConditionEntity = weatherCondition.toWeatherConditionEntity())

}