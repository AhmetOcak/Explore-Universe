package com.spaceapp.data.datasource.local.db.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.spaceapp.data.datasource.local.db.entity.PeopleInSpaceEntity
import com.spaceapp.data.utils.RoomTables

@Dao
interface PeopleInSpaceDao {

    @Insert
    suspend fun addPeople(peopleInSpaceEntity: PeopleInSpaceEntity)

    @Query("SELECT * FROM ${RoomTables.PEOPLE_IN_SPACE_TABLE}")
    suspend fun getPeopleInSpace(): List<PeopleInSpaceEntity>

    @Query("DELETE FROM ${RoomTables.PEOPLE_IN_SPACE_TABLE}")
    suspend fun deleteAll()
}