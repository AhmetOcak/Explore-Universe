package com.spaceapp.data.datasource.local.people_in_space

import com.spaceapp.data.datasource.local.people_in_space.db.entity.PeopleInSpaceEntity
import com.spaceapp.data.datasource.local.people_in_space.db.room.dao.PeopleInSpaceDao
import javax.inject.Inject

class PeopleInSpaceLocalDataSourceImpl @Inject constructor(private val peopleInSpaceDao: PeopleInSpaceDao) : PeopleInSpaceLocalDataSource {

    override suspend fun addPeopleInSpace(peopleInSpaceEntity: PeopleInSpaceEntity) =
        peopleInSpaceDao.addPeople(peopleInSpaceEntity = peopleInSpaceEntity)

    override suspend fun getPeopleInSpace() = peopleInSpaceDao.getPeopleInSpace()

    override suspend fun deleteAll() = peopleInSpaceDao.deleteAll()
}