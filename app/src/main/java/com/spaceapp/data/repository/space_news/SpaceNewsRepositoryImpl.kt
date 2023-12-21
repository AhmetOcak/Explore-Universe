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
    override suspend fun addSpaceToLocal(spaceNews: SpaceNews) {
        spaceNews.articles.forEach {
            localDataSource.addSpaceNews(spaceNewsEntity = it.toSpaceNewsEntity())
        }
    }

    override suspend fun getSpaceNewsFromLocal(): SpaceNews =
        localDataSource.getSpaceNews().toSpaceNews()

    override suspend fun deleteLocalSpaceNews() = localDataSource.deleteAll()

    override suspend fun getSpaceNewsFromNetwork(): SpaceNews =
        remoteDataSource.getSpaceNews().toSpaceNews()

    override suspend fun getLatestScienceNewsFromNetwork(): SpaceNews =
        remoteDataSource.getLatestScienceNews().toSpaceNews()
}