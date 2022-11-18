package com.spaceapp.data.datasource.remote.apod

import com.spaceapp.data.datasource.remote.apod.api.ApodApi
import javax.inject.Inject

class ApodRemoteDataSourceImpl @Inject constructor(private val api: ApodApi) : ApodRemoteDataSource {

    override suspend fun getApod() = api.getApod()
}