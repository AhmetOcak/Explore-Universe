package com.spaceapp.data.repository

import com.spaceapp.data.datasource.local.space_news.SpaceNewsLocalDataSourceImpl
import com.spaceapp.data.datasource.remote.space_news.SpaceNewsRemoteDataSourceImpl
import com.spaceapp.data.mappers.toSpaceNews
import com.spaceapp.data.mappers.toSpaceNewsEntity
import com.spaceapp.domain.model.space_news.SpaceNews
import com.spaceapp.domain.repository.SpaceNewsRepository
import javax.inject.Inject

class SpaceNewsRepositoryImpl @Inject constructor(
    private val localDataSource: SpaceNewsLocalDataSourceImpl,
    private val remoteDataSource: SpaceNewsRemoteDataSourceImpl
) : SpaceNewsRepository {

    override suspend fun addSpaceToLocal(spaceNews: SpaceNews) =
        localDataSource.addSpaceNews(spaceNewsEntity = spaceNews.toSpaceNewsEntity())

    override suspend fun getSpaceNewsFromLocal(): List<SpaceNews> =
        localDataSource.getSpaceNews().toSpaceNews()

    override suspend fun deleteLocalSpaceNews() = localDataSource.deleteAll()

    override suspend fun getSpaceNewsFromNetwork(): List<SpaceNews> =
        remoteDataSource.getSpaceNews().toSpaceNews()
}