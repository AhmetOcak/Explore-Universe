package com.spaceapp.data.datasource.local.people_in_space

import com.spaceapp.data.datasource.local.people_in_space.db.entity.PeopleInSpaceEntity
import com.spaceapp.data.datasource.local.people_in_space.db.room.dao.PeopleInSpaceDao
import javax.inject.Inject

class PeopleInSpaceLocalDataSource @Inject constructor(private val peopleInSpaceDao: PeopleInSpaceDao) {

    suspend fun addPeopleInSpace(peopleInSpaceEntity: PeopleInSpaceEntity) =
        peopleInSpaceDao.addPeople(peopleInSpaceEntity = peopleInSpaceEntity)

    suspend fun getPeopleInSpace() = peopleInSpaceDao.getPeopleInSpace()

    suspend fun deleteAll() = peopleInSpaceDao.deleteAll()
}