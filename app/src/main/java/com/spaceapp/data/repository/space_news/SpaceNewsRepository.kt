package com.spaceapp.data.repository.space_news

import com.spaceapp.domain.model.space_news.SpaceNews

interface SpaceNewsRepository {

/*    suspend fun addSpaceToLocal(spaceNews: SpaceNews)

    suspend fun getSpaceNewsFromLocal(): List<SpaceNews>

    suspend fun deleteLocalSpaceNews()*/

    suspend fun getSpaceNewsFromNetwork(): SpaceNews

    suspend fun getLatestScienceNewsFromNetwork(): SpaceNews
}