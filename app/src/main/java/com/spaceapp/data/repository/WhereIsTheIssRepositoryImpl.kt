package com.spaceapp.data.repository

import com.spaceapp.data.datasource.local.where_is_the_iss.WhereIsTheIssLocalDataSourceImpl
import com.spaceapp.data.datasource.remote.where_is_the_iss.WhereIsTheIssRemoteDataSourceImpl
import com.spaceapp.data.mappers.toIss
import com.spaceapp.data.mappers.toIssEntity
import com.spaceapp.domain.model.where_is_the_iss.Iss
import com.spaceapp.domain.repository.WhereIsTheIssRepository
import javax.inject.Inject

class WhereIsTheIssRepositoryImpl @Inject constructor(
    private val localDataSource: WhereIsTheIssLocalDataSourceImpl,
    private val remoteDataSource: WhereIsTheIssRemoteDataSourceImpl
) : WhereIsTheIssRepository {

    override suspend fun getIssPositionFromNetwork(): Iss =
        remoteDataSource.getIssPosition().toIss()

    override suspend fun getIssPositionFromLocal(): Iss =
        localDataSource.getIss().toIss()

    override suspend fun addIssPositionToLocal(iss: Iss) =
        localDataSource.addIss(issEntity = iss.toIssEntity())

    override suspend fun updateIssPosition(iss: Iss) =
        localDataSource.updateIss(issEntity = iss.toIssEntity())
}