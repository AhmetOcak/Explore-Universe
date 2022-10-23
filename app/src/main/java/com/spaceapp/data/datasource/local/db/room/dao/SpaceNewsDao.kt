package com.spaceapp.data.datasource.local.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.spaceapp.data.datasource.local.db.entity.SpaceNewsEntity
import com.spaceapp.data.utils.RoomTables

@Dao
interface SpaceNewsDao {

    @Insert
    suspend fun addSpaceNews(spaceNewsEntity: SpaceNewsEntity)

    @Query("SELECT * FROM ${RoomTables.SPACE_NEWS_TABLE}")
    suspend fun getSpaceNews(): List<SpaceNewsEntity>

    @Query("DELETE FROM ${RoomTables.SPACE_NEWS_TABLE}")
    suspend fun deleteAll()
}