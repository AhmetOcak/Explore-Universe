package com.spaceapp.data.datasource.remote.mars_photos

import com.spaceapp.data.datasource.remote.mars_photos.api.MarsPhotoApi
import javax.inject.Inject

class MarsPhotoRemoteDataSource @Inject constructor(private val api: MarsPhotoApi) {

    suspend fun getLatestMarsPhotos() = api.getMarsLatestPhotos()
}