package com.spaceapp.data.datasource.remote.space_news

import com.spaceapp.data.datasource.remote.space_news.api.SpaceNewsApi
import javax.inject.Inject

class SpaceNewsRemoteDataSource @Inject constructor(private val api: SpaceNewsApi){

    suspend fun getSpaceNews() = api.getSpaceNews()
}