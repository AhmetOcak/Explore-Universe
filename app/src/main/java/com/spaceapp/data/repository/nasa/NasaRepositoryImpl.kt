package com.spaceapp.data.repository.nasa

import com.spaceapp.data.datasource.local.apod.ApodLocalDataSource
import com.spaceapp.data.datasource.local.mars_photos.MarsPhotosLocalDataSource
import com.spaceapp.data.datasource.remote.apod.ApodRemoteDataSource
import com.spaceapp.data.datasource.remote.mars_photos.MarsPhotoRemoteDataSource
import com.spaceapp.data.mappers.toApod
import com.spaceapp.data.mappers.toApodEntity
import com.spaceapp.data.mappers.toMarsPhoto
import com.spaceapp.data.mappers.toMarsPhotoEntity
import com.spaceapp.domain.model.apod.Apod
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import javax.inject.Inject

class NasaRepositoryImpl @Inject constructor(
    private val remoteApodDataSource: ApodRemoteDataSource,
    private val localApodDataSource: ApodLocalDataSource,
    private val localMarsDataSource: MarsPhotosLocalDataSource,
    private val remoteMarsDataSource: MarsPhotoRemoteDataSource
) : NasaRepository {
    override suspend fun addApodToLocal(apod: Apod) =
        localApodDataSource.addApod(apodEntity = apod.toApodEntity())

    override suspend fun getApodFromNetwork(): List<Apod> =
        remoteApodDataSource.getApod().toApod()

    override suspend fun getApodFromLocal(): List<Apod> =
        localApodDataSource.getApods().toApod()

    override suspend fun deleteLocalApod() = localApodDataSource.deleteAll()

    override suspend fun addMarsPhotoToLocal(marsPhoto: MarsPhoto) =
        localMarsDataSource.addMarsPhoto(marsPhotoEntity = marsPhoto.toMarsPhotoEntity())

    override suspend fun getMarsPhotoFromLocal(): List<MarsPhoto> =
        localMarsDataSource.getMarsPhotos().toMarsPhoto()

    override suspend fun deleteLocalMars() = localMarsDataSource.deleteAll()

    override suspend fun getLatestMarsPhotos(): List<MarsPhoto> =
        remoteMarsDataSource.getLatestMarsPhotos().toMarsPhoto()
}