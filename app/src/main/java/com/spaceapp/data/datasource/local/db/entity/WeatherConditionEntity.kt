package com.spaceapp.data.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spaceapp.data.utils.RoomTables

@Entity(tableName = RoomTables.WEATHER_COND_TABLE)
data class WeatherConditionEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "temp")
    var temp: Double,

    @ColumnInfo(name = "main_description")
    var mainDescription: String,

    @ColumnInfo(name = "description")
    var description: String
)
