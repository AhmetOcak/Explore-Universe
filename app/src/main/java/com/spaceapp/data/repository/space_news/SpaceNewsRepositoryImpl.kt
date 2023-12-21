package com.spaceapp.data.repository.space_news

import com.spaceapp.data.datasource.local.space_news.SpaceNewsLocalDataSource
import com.spaceapp.data.datasource.remote.space_news.SpaceNewsRemoteDataSource
import com.spaceapp.data.mappers.toSpaceNews
import com.spaceapp.data.mappers.toSpaceNewsEntity
import com.spaceapp.domain.model.space_news.SpaceNews
import javax.inject.Inject

class SpaceNewsRepositoryImpl @Inject constructor(
    private val localDataSource: SpaceNewsLocalDataSource,
    private val remoteDataSource: SpaceNewsRemoteDataSource
) : SpaceNewsRepository {

    override suspend fun addSpaceToLocal(spaceNews: SpaceNews) =
        localDataSource.addSpaceNews(spaceNewsEntity = spaceNews.toSpaceNewsEntity())

    override suspend fun getSpaceNewsFromLocal(): List<SpaceNews> =
        localDataSource.getSpaceNews().toSpaceNews()

    override suspend fun deleteLocalSpaceNews() = localDataSource.deleteAll()

    override suspend fun getSpaceNewsFromNetwork(): List<SpaceNews> =
        remoteDataSource.getSpaceNews().toSpaceNews()
}