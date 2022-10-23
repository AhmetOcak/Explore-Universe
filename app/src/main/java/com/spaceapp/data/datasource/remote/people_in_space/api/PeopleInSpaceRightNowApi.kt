package com.spaceapp.data.datasource.remote.people_in_space.api

import com.spaceapp.data.datasource.remote.people_in_space.entity.PeopleInSpaceDto
import retrofit2.http.GET

interface PeopleInSpaceRightNowApi {
    @GET("/astros.json")
    suspend fun getPeopleInSpaceRightNow() : PeopleInSpaceDto
}