package com.spaceapp.data.datasource.local.apod

import com.spaceapp.data.datasource.local.apod.db.entity.ApodEntity

interface ApodLocalDataSource {

    suspend fun addApod(apodEntity: ApodEntity)

    suspend fun getApods() :  List<ApodEntity>

    suspend fun deleteAll()
}