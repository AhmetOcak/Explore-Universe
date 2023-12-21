package com.spaceapp.data.datasource.local.space_news.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.spaceapp.data.datasource.local.space_news.db.entity.ScienceNewsEntity
import com.spaceapp.data.utils.RoomTables

@Dao
interface ScienceNewsDao {

    @Insert
    suspend fun addScienceNews(spaceNewsEntity: ScienceNewsEntity)

    @Query("SELECT * FROM ${RoomTables.SCIENCE_NEWS_TABLE}")
    suspend fun getScienceNews(): List<ScienceNewsEntity>

    @Query("DELETE FROM ${RoomTables.SCIENCE_NEWS_TABLE}")
    suspend fun deleteAll()
}