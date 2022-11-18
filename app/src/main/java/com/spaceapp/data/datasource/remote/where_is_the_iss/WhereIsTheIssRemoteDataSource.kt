package com.spaceapp.data.datasource.remote.where_is_the_iss

import com.spaceapp.data.datasource.remote.where_is_the_iss.entity.IssDto

interface WhereIsTheIssRemoteDataSource {

    suspend fun getIssPosition() : IssDto
}