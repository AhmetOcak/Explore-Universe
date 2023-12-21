package com.spaceapp.data.datasource.remote.space_news

import com.spaceapp.data.datasource.remote.space_news.entity.SpaceNewsDto

interface SpaceNewsRemoteDataSource {

    suspend fun getSpaceNews(): SpaceNewsDto

    suspend fun getLatestScienceNews(): SpaceNewsDto
}