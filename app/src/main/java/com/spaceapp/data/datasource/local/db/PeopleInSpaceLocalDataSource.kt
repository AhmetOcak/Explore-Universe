package com.spaceapp.data.datasource.local.db

import com.spaceapp.data.datasource.local.db.entity.PeopleInSpaceEntity
import com.spaceapp.data.datasource.local.db.room.dao.PeopleInSpaceDao
import javax.inject.Inject

class PeopleInSpaceLocalDataSource @Inject constructor(private val peopleInSpaceDao: PeopleInSpaceDao) {

    suspend fun addPeopleInSpace(peopleInSpaceEntity: PeopleInSpaceEntity) =
        peopleInSpaceDao.addPeople(peopleInSpaceEntity = peopleInSpaceEntity)

    suspend fun getPeopleInSpace() = peopleInSpaceDao.getPeopleInSpace()

    suspend fun deleteAll() = peopleInSpaceDao.deleteAll()
}