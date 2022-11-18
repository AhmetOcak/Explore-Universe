package com.spaceapp.data.datasource.local.mars_photos

import com.spaceapp.data.datasource.local.mars_photos.db.entity.MarsPhotoEntity
import com.spaceapp.data.datasource.local.mars_photos.db.room.dao.MarsPhotoDao
import javax.inject.Inject

class MarsPhotosLocalDataSourceImpl @Inject constructor(private val marsPhotoDao: MarsPhotoDao) : MarsPhotosLocalDataSource {

    override suspend fun addMarsPhoto(marsPhotoEntity: MarsPhotoEntity) =
        marsPhotoDao.addMarsPhoto(marsPhotoEntity = marsPhotoEntity)

    override suspend fun getMarsPhotos() = marsPhotoDao.getMarsPhotos()

    override suspend fun deleteAll() = marsPhotoDao.deleteAll()
}