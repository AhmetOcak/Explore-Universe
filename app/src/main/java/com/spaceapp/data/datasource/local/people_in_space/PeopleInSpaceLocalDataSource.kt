package com.spaceapp.data.datasource.local.people_in_space

import com.spaceapp.data.datasource.local.people_in_space.db.entity.PeopleInSpaceEntity

interface PeopleInSpaceLocalDataSource {

    suspend fun addPeopleInSpace(peopleInSpaceEntity: PeopleInSpaceEntity)

    suspend fun getPeopleInSpace() : List<PeopleInSpaceEntity>

    suspend fun deleteAll()
}