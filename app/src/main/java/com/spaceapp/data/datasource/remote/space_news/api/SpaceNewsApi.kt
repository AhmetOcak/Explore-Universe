package com.spaceapp.data.datasource.remote.space_news.api

import com.spaceapp.data.datasource.remote.space_news.entity.SpaceNewsDto
import retrofit2.http.GET
import retrofit2.http.Headers

interface SpaceNewsApi {

    @Headers(
        "X-RapidAPI-Key: 164d3cb6a1mshc96bd41643952fep18d32ejsna274852581d2",
        "X-RapidAPI-Host: spacefo.p.rapidapi.com"
    )
    @GET("/articles")
    suspend fun getSpaceNews(): List<SpaceNewsDto>
}