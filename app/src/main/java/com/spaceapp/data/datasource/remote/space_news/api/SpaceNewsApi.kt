package com.spaceapp.data.datasource.remote.space_news.api

import com.spaceapp.BuildConfig
import com.spaceapp.data.datasource.remote.space_news.entity.SpaceNewsDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface SpaceNewsApi {

    @Headers("X-Api-Key: ${BuildConfig.NEWS_API_KEY}")
    @GET("/v2/everything?q=Space")
    suspend fun getSpaceNews(): SpaceNewsDto

    @Headers("X-Api-Key: ${BuildConfig.NEWS_API_KEY}")
    @GET("/v2/everything?q=Technology")
    suspend fun getLatestScienceNews(): SpaceNewsDto
}