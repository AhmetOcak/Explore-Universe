package com.spaceapp.data.datasource.remote.apod

import com.spaceapp.data.datasource.remote.apod.entity.ApodDto

interface ApodRemoteDataSource {

    suspend fun getApod() : List<ApodDto>
}