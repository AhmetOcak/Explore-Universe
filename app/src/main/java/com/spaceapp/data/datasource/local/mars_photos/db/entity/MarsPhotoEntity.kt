package com.spaceapp.data.datasource.local.mars_photos.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spaceapp.data.utils.RoomTables

@Entity(tableName = RoomTables.MARS_PHOTO_TABLE)
data class MarsPhotoEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "rover_name")
    var roverName: String,

    @ColumnInfo(name = "image_url")
    var image: String,

    @ColumnInfo(name = "date")
    var date: String,

)
