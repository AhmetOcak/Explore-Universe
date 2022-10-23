package com.spaceapp.data.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.spaceapp.data.utils.RoomTables

@Entity(tableName = RoomTables.PEOPLE_IN_SPACE_TABLE)
data class PeopleInSpaceEntity(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "craft")
    var craft: String
)
