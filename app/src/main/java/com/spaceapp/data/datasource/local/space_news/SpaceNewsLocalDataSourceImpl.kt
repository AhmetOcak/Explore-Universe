package com.spaceapp.data.datasource.local.space_news

import com.spaceapp.data.datasource.local.space_news.db.entity.SpaceNewsEntity
import com.spaceapp.data.datasource.local.space_news.db.room.dao.SpaceNewsDao
import javax.inject.Inject

class SpaceNewsLocalDataSourceImpl @Inject constructor(private val spaceNewsDao: SpaceNewsDao) : SpaceNewsLocalDataSource{

    override suspend fun addSpaceNews(spaceNewsEntity: SpaceNewsEntity) =
        spaceNewsDao.addSpaceNews(spaceNewsEntity = spaceNewsEntity)

    override suspend fun getSpaceNews() = spaceNewsDao.getSpaceNews()

    override suspend fun deleteAll() = spaceNewsDao.deleteAll()
}