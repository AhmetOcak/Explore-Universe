package com.spaceapp.data.datasource.local.weather_condition

import com.spaceapp.data.datasource.local.weather_condition.db.entity.WeatherConditionEntity
import com.spaceapp.data.datasource.local.weather_condition.db.room.dao.WeatherConditionDao
import javax.inject.Inject

class WeatherConditionLocalDataSourceImpl @Inject constructor(private val weatherConditionDao: WeatherConditionDao) : WeatherConditionLocalDataSource {

    override suspend fun addWeatherCondition(weatherConditionEntity: WeatherConditionEntity) =
        weatherConditionDao.addWeatherCondition(weatherConditionEntity = weatherConditionEntity)

    override suspend fun getWeatherCondition() = weatherConditionDao.getWeatherCondition()

    override suspend fun updateWeatherCondition(weatherConditionEntity: WeatherConditionEntity) =
        weatherConditionDao.updateWeatherCondition(weatherConditionEntity = weatherConditionEntity)
}