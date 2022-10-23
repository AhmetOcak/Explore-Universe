package com.spaceapp.data.datasource.remote.apod.api

import com.spaceapp.data.datasource.remote.apod.entity.ApodDto
import retrofit2.http.GET
import retrofit2.http.Query

// Astronomy Picture of the Day (APOD)

interface ApodApi {

    @GET("/planetary/apod")
    suspend fun getApod(
        @Query("api_key") apiKey: String = "D48DKgPFXWsX5KdnpgTMIvV9toJRvfwjT3SrFgdm",
        @Query("count") count: Int = 10
    ): List<ApodDto>
}