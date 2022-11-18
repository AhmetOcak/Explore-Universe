package com.spaceapp.data.datasource.local.mars_photos

import com.spaceapp.data.datasource.local.mars_photos.db.entity.MarsPhotoEntity
import com.spaceapp.data.datasource.local.mars_photos.db.room.dao.MarsPhotoDao
import javax.inject.Inject

class MarsPhotosLocalDataSource @Inject constructor(private val marsPhotoDao: MarsPhotoDao) {

    suspend fun addMarsPhoto(marsPhotoEntity: MarsPhotoEntity) =
        marsPhotoDao.addMarsPhoto(marsPhotoEntity = marsPhotoEntity)

    suspend fun getMarsPhotos() = marsPhotoDao.getMarsPhotos()

    suspend fun deleteAll() = marsPhotoDao.deleteAll()
}