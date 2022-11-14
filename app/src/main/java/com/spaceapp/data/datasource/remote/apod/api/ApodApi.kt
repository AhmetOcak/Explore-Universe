package com.spaceapp.data.datasource.remote.apod.api

import com.spaceapp.BuildConfig
import com.spaceapp.data.datasource.remote.apod.entity.ApodDto
import retrofit2.http.GET
import retrofit2.http.Query

// Astronomy Picture of the Day (APOD)

interface ApodApi {

    @GET("/planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY,
        @Query("count") count: Int = 10
    ): List<ApodDto>
}