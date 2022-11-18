package com.spaceapp.data.datasource.local.apod

import com.spaceapp.data.datasource.local.apod.db.entity.ApodEntity
import com.spaceapp.data.datasource.local.apod.db.room.dao.ApodDao
import javax.inject.Inject

class ApodLocalDataSourceImpl @Inject constructor(private val apodDao: ApodDao) : ApodLocalDataSource{

    override suspend fun addApod(apodEntity: ApodEntity) = apodDao.addApod(apodEntity = apodEntity)

    override suspend fun getApods() = apodDao.getApods()

    override suspend fun deleteAll() = apodDao.deleteAll()
}