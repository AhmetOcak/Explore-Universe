package com.spaceapp.data.repository

import com.spaceapp.data.datasource.local.db.PeopleInSpaceLocalDataSource
import com.spaceapp.data.datasource.remote.people_in_space.PeopleInSpaceRemoteDataSource
import com.spaceapp.data.mappers.toPeopleInSpace
import com.spaceapp.data.mappers.toPeopleInSpaceEntity
import com.spaceapp.domain.model.PeopleInSpace
import com.spaceapp.domain.repository.PeopleInSpaceRepository
import javax.inject.Inject

class PeopleInSpaceRepositoryImpl @Inject constructor(
    private val localDataSource: PeopleInSpaceLocalDataSource,
    private val remoteDataSource: PeopleInSpaceRemoteDataSource
) : PeopleInSpaceRepository {

    override suspend fun addPeopleInSpaceToLocal(peopleInSpace: PeopleInSpace) =
        localDataSource.addPeopleInSpace(peopleInSpaceEntity = peopleInSpace.toPeopleInSpaceEntity())

    override suspend fun getPeopleInSpaceFromLocal(): List<PeopleInSpace> =
        localDataSource.getPeopleInSpace().toPeopleInSpace()

    override suspend fun deleteLocalPeopleInSpace() = localDataSource.deleteAll()

    override suspend fun getPeopleInSpaceRightNow(): PeopleInSpace =
        remoteDataSource.getPeopleInSpaceRightNow().toPeopleInSpace()

}