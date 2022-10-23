package com.spaceapp.domain.repository

import com.spaceapp.domain.model.PeopleInSpace

interface PeopleInSpaceRepository {

    suspend fun addPeopleInSpaceToLocal(peopleInSpace: PeopleInSpace)

    suspend fun getPeopleInSpaceFromLocal(): List<PeopleInSpace>

    suspend fun deleteLocalPeopleInSpace()

    suspend fun getPeopleInSpaceRightNow(): PeopleInSpace
}