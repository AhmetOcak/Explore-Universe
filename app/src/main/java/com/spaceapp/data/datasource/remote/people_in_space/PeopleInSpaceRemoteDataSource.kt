package com.spaceapp.data.datasource.remote.people_in_space

import com.spaceapp.data.datasource.remote.people_in_space.entity.PeopleInSpaceDto

interface PeopleInSpaceRemoteDataSource {

    suspend fun getPeopleInSpaceRightNow() : PeopleInSpaceDto
}