package com.spaceapp.data.datasource.local.weather_condition

import com.spaceapp.data.datasource.local.weather_condition.db.entity.WeatherConditionEntity
import com.spaceapp.data.datasource.local.weather_condition.db.room.dao.WeatherConditionDao
import javax.inject.Inject

class WeatherConditionLocalDataSource @Inject constructor(private val weatherConditionDao: WeatherConditionDao) {

    suspend fun addWeatherCondition(weatherConditionEntity: WeatherConditionEntity) =
        weatherConditionDao.addWeatherCondition(weatherConditionEntity = weatherConditionEntity)

    suspend fun getWeatherCondition() = weatherConditionDao.getWeatherCondition()

    suspend fun updateWeatherCondition(weatherConditionEntity: WeatherConditionEntity) =
        weatherConditionDao.updateWeatherCondition(weatherConditionEntity = weatherConditionEntity)
}