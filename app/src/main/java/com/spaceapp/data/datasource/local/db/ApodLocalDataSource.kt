package com.spaceapp.data.datasource.local.db

import com.spaceapp.data.datasource.local.db.entity.ApodEntity
import com.spaceapp.data.datasource.local.db.room.dao.ApodDao
import javax.inject.Inject

class ApodLocalDataSource @Inject constructor(private val apodDao: ApodDao) {

    suspend fun addApod(apodEntity: ApodEntity) = apodDao.addApod(apodEntity = apodEntity)

    suspend fun getApods() = apodDao.getApods()

    suspend fun deleteAll() = apodDao.deleteAll()
}