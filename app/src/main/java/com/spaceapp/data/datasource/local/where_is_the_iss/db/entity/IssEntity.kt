package com.spaceapp.data.datasource.local.where_is_the_iss.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spaceapp.data.utils.RoomTables

@Entity(tableName = RoomTables.ISS_TABLE)
data class IssEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "latitude")
    var latitude: Double,

    @ColumnInfo(name = "longitude")
    var longitude: Double,

    @ColumnInfo(name = "altitude")
    var altitude: Double,

    @ColumnInfo(name = "velocity")
    var velocity: Double,

    @ColumnInfo(name = "visibility")
    var visibility: String,

    @ColumnInfo(name = "timestamp")
    var date: Long
)
