package com.spaceapp.data.datasource.local.apod.db.room.dao

import androidx.room.*
import com.spaceapp.data.datasource.local.apod.db.entity.ApodEntity
import com.spaceapp.data.utils.RoomTables

@Dao
interface ApodDao {

    @Insert
    suspend fun addApod(apodEntity: ApodEntity)

    @Query("SELECT * FROM ${RoomTables.APOD_TABLE}")
    suspend fun getApods(): List<ApodEntity>

    @Query("DELETE FROM ${RoomTables.APOD_TABLE}")
    suspend fun deleteAll()
}