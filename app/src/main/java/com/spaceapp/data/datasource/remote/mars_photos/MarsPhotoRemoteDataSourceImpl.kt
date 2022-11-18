package com.spaceapp.data.datasource.remote.mars_photos

import com.spaceapp.data.datasource.remote.mars_photos.api.MarsPhotoApi
import javax.inject.Inject

class MarsPhotoRemoteDataSourceImpl @Inject constructor(private val api: MarsPhotoApi) : MarsPhotoRemoteDataSource {

    override suspend fun getLatestMarsPhotos() = api.getMarsLatestPhotos()
}