package com.spaceapp.data.datasource.local.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.spaceapp.data.datasource.local.db.entity.MarsPhotoEntity
import com.spaceapp.data.utils.RoomTables

@Dao
interface MarsPhotoDao {

    @Insert
    suspend fun addMarsPhoto(marsPhotoEntity: MarsPhotoEntity)

    @Query("SELECT * FROM ${RoomTables.MARS_PHOTO_TABLE}")
    suspend fun getMarsPhotos(): List<MarsPhotoEntity>

    @Query("DELETE FROM ${RoomTables.MARS_PHOTO_TABLE}")
    suspend fun deleteAll()
}