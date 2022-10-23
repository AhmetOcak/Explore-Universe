package com.spaceapp.data.datasource.remote.where_is_the_iss.api

import com.spaceapp.data.datasource.remote.where_is_the_iss.entity.IssDto
import retrofit2.http.GET

interface WhereIsTheIssApi {

    @GET("satellites/25544")
    suspend fun getIssPosition() : IssDto
}