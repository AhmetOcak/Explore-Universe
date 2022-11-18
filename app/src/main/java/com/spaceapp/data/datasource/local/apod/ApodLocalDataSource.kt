package com.spaceapp.data.datasource.local.apod

import com.spaceapp.data.datasource.local.apod.db.entity.ApodEntity
import com.spaceapp.data.datasource.local.apod.db.room.dao.ApodDao
import javax.inject.Inject

class ApodLocalDataSource @Inject constructor(private val apodDao: ApodDao) {

    suspend fun addApod(apodEntity: ApodEntity) = apodDao.addApod(apodEntity = apodEntity)

    suspend fun getApods() = apodDao.getApods()

    suspend fun deleteAll() = apodDao.deleteAll()
}