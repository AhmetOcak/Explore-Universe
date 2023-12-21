package com.spaceapp.data.repository.iss

import com.spaceapp.data.datasource.local.where_is_the_iss.WhereIsTheIssLocalDataSource
import com.spaceapp.data.datasource.remote.where_is_the_iss.WhereIsTheIssRemoteDataSource
import com.spaceapp.data.mappers.toIss
import com.spaceapp.data.mappers.toIssEntity
import com.spaceapp.domain.model.where_is_the_iss.Iss
import javax.inject.Inject

class WhereIsTheIssRepositoryImpl @Inject constructor(
    private val localDataSource: WhereIsTheIssLocalDataSource,
    private val remoteDataSource: WhereIsTheIssRemoteDataSource
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