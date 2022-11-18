package com.spaceapp.data.datasource.local.space_news

import com.spaceapp.data.datasource.local.space_news.db.entity.SpaceNewsEntity

interface SpaceNewsLocalDataSource {

    suspend fun addSpaceNews(spaceNewsEntity: SpaceNewsEntity)

    suspend fun getSpaceNews() : List<SpaceNewsEntity>

    suspend fun deleteAll()
}