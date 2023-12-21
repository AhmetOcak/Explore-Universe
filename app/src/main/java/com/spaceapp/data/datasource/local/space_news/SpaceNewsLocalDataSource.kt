package com.spaceapp.data.datasource.local.space_news

import com.spaceapp.data.datasource.local.space_news.db.entity.ScienceNewsEntity
import com.spaceapp.data.datasource.local.space_news.db.entity.SpaceNewsEntity

interface SpaceNewsLocalDataSource {

    suspend fun addSpaceNews(spaceNewsEntity: SpaceNewsEntity)

    suspend fun getSpaceNews() : List<SpaceNewsEntity>

    suspend fun deleteAllSpaceNews()

    suspend fun addScienceNews(spaceNewsEntity: ScienceNewsEntity)

    suspend fun getScienceNews() : List<ScienceNewsEntity>

    suspend fun deleteAllScienceNews()
}