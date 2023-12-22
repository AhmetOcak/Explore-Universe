package com.spaceapp.domain.repository

import com.spaceapp.domain.model.space_news.SpaceNews

interface SpaceNewsRepository {

   suspend fun addSpaceToLocal(spaceNews: SpaceNews)

    suspend fun getSpaceNewsFromLocal(): SpaceNews

    suspend fun deleteLocalSpaceNews()

    suspend fun getSpaceNewsFromNetwork(): SpaceNews

    suspend fun getLatestScienceNewsFromNetwork(): SpaceNews

    suspend fun addScienceNews(spaceNews: SpaceNews)

    suspend fun getScienceNewsFromLocal() : SpaceNews

    suspend fun deleteAllScienceNews()
}