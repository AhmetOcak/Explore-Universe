package com.spaceapp.data.datasource.remote.mars_photos.api

import com.spaceapp.BuildConfig
import com.spaceapp.data.datasource.remote.mars_photos.entity.MarsPhotoDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MarsPhotoApi {

    @GET("rovers/curiosity/latest_photos")
    suspend fun getMarsLatestPhotos(
        @Query("api_key") apiKey: String = BuildConfig.NASA_API_KEY
    ) : MarsPhotoDto
}