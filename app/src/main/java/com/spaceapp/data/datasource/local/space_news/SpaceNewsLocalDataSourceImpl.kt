package com.spaceapp.data.datasource.local.space_news

import com.spaceapp.data.datasource.local.space_news.db.entity.ScienceNewsEntity
import com.spaceapp.data.datasource.local.space_news.db.entity.SpaceNewsEntity
import com.spaceapp.data.datasource.local.space_news.db.room.dao.ScienceNewsDao
import com.spaceapp.data.datasource.local.space_news.db.room.dao.SpaceNewsDao
import javax.inject.Inject

class SpaceNewsLocalDataSourceImpl @Inject constructor(
    private val spaceNewsDao: SpaceNewsDao,
    private val scienceNewsDao: ScienceNewsDao
) : SpaceNewsLocalDataSource {

    override suspend fun addSpaceNews(spaceNewsEntity: SpaceNewsEntity) =
        spaceNewsDao.addSpaceNews(spaceNewsEntity = spaceNewsEntity)

    override suspend fun getSpaceNews() = spaceNewsDao.getSpaceNews()

    override suspend fun deleteAllSpaceNews() = spaceNewsDao.deleteAll()
    override suspend fun addScienceNews(spaceNewsEntity: ScienceNewsEntity) =
        scienceNewsDao.addScienceNews(spaceNewsEntity)

    override suspend fun getScienceNews() = scienceNewsDao.getScienceNews()

    override suspend fun deleteAllScienceNews() = scienceNewsDao.deleteAll()
}