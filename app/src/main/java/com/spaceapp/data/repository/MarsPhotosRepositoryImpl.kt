package com.spaceapp.data.repository

import com.spaceapp.data.datasource.local.mars_photos.MarsPhotosLocalDataSourceImpl
import com.spaceapp.data.datasource.remote.mars_photos.MarsPhotoRemoteDataSource
import com.spaceapp.data.mappers.toMarsPhoto
import com.spaceapp.data.mappers.toMarsPhotoEntity
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import com.spaceapp.domain.repository.MarsPhotosRepository
import javax.inject.Inject

class MarsPhotosRepositoryImpl @Inject constructor(
    private val localDataSource: MarsPhotosLocalDataSourceImpl,
    private val remoteDataSource: MarsPhotoRemoteDataSource
) : MarsPhotosRepository {

    override suspend fun addMarsPhotoToLocal(marsPhoto: MarsPhoto) =
        localDataSource.addMarsPhoto(marsPhotoEntity = marsPhoto.toMarsPhotoEntity())

    override suspend fun getMarsPhotoFromLocal(): List<MarsPhoto> =
        localDataSource.getMarsPhotos().toMarsPhoto()

    override suspend fun deleteLocalMars() = localDataSource.deleteAll()

    override suspend fun getLatestMarsPhotos(): List<MarsPhoto> =
        remoteDataSource.getLatestMarsPhotos().toMarsPhoto()
}