package com.spaceapp.data.repository

import com.spaceapp.data.datasource.local.db.SpaceNewsLocalDataSource
import com.spaceapp.data.datasource.remote.space_news.SpaceNewsRemoteDataSource
import com.spaceapp.data.mappers.toSpaceNews
import com.spaceapp.data.mappers.toSpaceNewsEntity
import com.spaceapp.domain.model.SpaceNews
import com.spaceapp.domain.repository.SpaceNewsRepository
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