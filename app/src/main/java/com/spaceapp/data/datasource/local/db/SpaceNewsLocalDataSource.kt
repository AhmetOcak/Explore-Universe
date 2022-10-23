package com.spaceapp.data.datasource.local.db

import com.spaceapp.data.datasource.local.db.entity.SpaceNewsEntity
import com.spaceapp.data.datasource.local.db.room.dao.SpaceNewsDao
import javax.inject.Inject

class SpaceNewsLocalDataSource @Inject constructor(private val spaceNewsDao: SpaceNewsDao) {

    suspend fun addSpaceNews(spaceNewsEntity: SpaceNewsEntity) =
        spaceNewsDao.addSpaceNews(spaceNewsEntity = spaceNewsEntity)

    suspend fun getSpaceNews() = spaceNewsDao.getSpaceNews()

    suspend fun deleteAll() = spaceNewsDao.deleteAll()
}