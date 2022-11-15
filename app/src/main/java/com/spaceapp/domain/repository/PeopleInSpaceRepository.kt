package com.spaceapp.domain.repository

import com.spaceapp.domain.model.people_in_space.PeopleInSpace

interface PeopleInSpaceRepository {

    suspend fun addPeopleInSpaceToLocal(peopleInSpace: PeopleInSpace)

    suspend fun getPeopleInSpaceFromLocal(): List<PeopleInSpace>

    suspend fun deleteLocalPeopleInSpace()

    suspend fun getPeopleInSpaceRightNow(): PeopleInSpace
}