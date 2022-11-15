package com.spaceapp.data.repository

import com.spaceapp.data.datasource.local.db.ApodLocalDataSource
import com.spaceapp.data.datasource.remote.apod.ApodRemoteDataSource
import com.spaceapp.data.mappers.toApod
import com.spaceapp.data.mappers.toApodEntity
import com.spaceapp.domain.model.apod.Apod
import com.spaceapp.domain.repository.ApodRepository
import javax.inject.Inject

class ApodRepositoryImpl @Inject constructor(
    private val remoteDataSource: ApodRemoteDataSource,
    private val localDataSource: ApodLocalDataSource
) : ApodRepository {

    override suspend fun addApodToLocal(apod: Apod) =
        localDataSource.addApod(apodEntity = apod.toApodEntity())

    override suspend fun getApodFromNetwork(): List<Apod> =
        remoteDataSource.getApod().toApod()

    override suspend fun getApodFromLocal(): List<Apod> =
        localDataSource.getApods().toApod()

    override suspend fun deleteLocalApod() = localDataSource.deleteAll()
}