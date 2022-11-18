package com.spaceapp.data.datasource.local.mars_photos

import com.spaceapp.data.datasource.local.mars_photos.db.entity.MarsPhotoEntity

interface MarsPhotosLocalDataSource {

    suspend fun addMarsPhoto(marsPhotoEntity: MarsPhotoEntity)

    suspend fun getMarsPhotos() : List<MarsPhotoEntity>

    suspend fun deleteAll()
}