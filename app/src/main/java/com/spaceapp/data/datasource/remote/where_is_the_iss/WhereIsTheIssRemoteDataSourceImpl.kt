package com.spaceapp.data.datasource.remote.where_is_the_iss

import com.spaceapp.data.datasource.remote.where_is_the_iss.api.WhereIsTheIssApi
import javax.inject.Inject

class WhereIsTheIssRemoteDataSourceImpl @Inject constructor(private val api: WhereIsTheIssApi) : WhereIsTheIssRemoteDataSource {

    override suspend fun getIssPosition() = api.getIssPosition()
}