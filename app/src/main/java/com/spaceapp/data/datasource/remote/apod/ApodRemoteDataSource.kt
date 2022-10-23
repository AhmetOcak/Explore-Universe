package com.spaceapp.data.datasource.remote.apod

import com.spaceapp.data.datasource.remote.apod.api.ApodApi
import javax.inject.Inject

class ApodRemoteDataSource @Inject constructor(private val api: ApodApi) {

    suspend fun getApod() = api.getApod()
}