package com.spaceapp.data.datasource.remote.space_news.api

import com.spaceapp.BuildConfig
import com.spaceapp.data.datasource.remote.space_news.entity.SpaceNewsDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface SpaceNewsApi {

    @Headers(
        "X-RapidAPI-Key: ${BuildConfig.SPACE_NEWS_API_KEY}",
        "X-RapidAPI-Host: spacefo.p.rapidapi.com"
    )
    @GET("/articles")
    suspend fun getSpaceNews(): List<SpaceNewsDto>
}