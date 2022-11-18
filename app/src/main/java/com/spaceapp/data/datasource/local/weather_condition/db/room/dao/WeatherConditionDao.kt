package com.spaceapp.data.datasource.local.weather_condition.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.spaceapp.data.datasource.local.weather_condition.db.entity.WeatherConditionEntity
import com.spaceapp.data.utils.RoomTables

@Dao
interface WeatherConditionDao {

    @Insert
    suspend fun addWeatherCondition(weatherConditionEntity: WeatherConditionEntity)

    @Query("SELECT * FROM ${RoomTables.WEATHER_COND_TABLE}")
    suspend fun getWeatherCondition(): WeatherConditionEntity

    @Update
    suspend fun updateWeatherCondition(weatherConditionEntity: WeatherConditionEntity)
}