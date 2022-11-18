package com.spaceapp.data.datasource.remote.mars_photos

import com.spaceapp.data.datasource.remote.mars_photos.entity.MarsPhotoDto

interface MarsPhotoRemoteDataSource {

    suspend fun getLatestMarsPhotos() : MarsPhotoDto
}