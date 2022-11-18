package com.spaceapp.data.datasource.remote.people_in_space

import com.spaceapp.data.datasource.remote.people_in_space.api.PeopleInSpaceRightNowApi
import javax.inject.Inject

class PeopleInSpaceRemoteDataSourceImpl @Inject constructor(private val api: PeopleInSpaceRightNowApi) {

    suspend fun getPeopleInSpaceRightNow() = api.getPeopleInSpaceRightNow()
}