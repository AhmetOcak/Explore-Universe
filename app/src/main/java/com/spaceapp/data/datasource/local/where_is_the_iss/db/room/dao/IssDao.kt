package com.spaceapp.data.datasource.local.where_is_the_iss.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.spaceapp.data.datasource.local.where_is_the_iss.db.entity.IssEntity
import com.spaceapp.data.utils.RoomTables

@Dao
interface IssDao {

    @Insert
    suspend fun addIss(issEntity: IssEntity)

    @Query("SELECT * FROM ${RoomTables.ISS_TABLE}")
    suspend fun getIss(): IssEntity

    @Update
    suspend fun updateIss(issEntity: IssEntity)
}