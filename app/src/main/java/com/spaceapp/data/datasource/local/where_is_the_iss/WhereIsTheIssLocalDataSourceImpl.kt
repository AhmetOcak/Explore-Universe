package com.spaceapp.data.datasource.local.where_is_the_iss

import com.spaceapp.data.datasource.local.where_is_the_iss.db.entity.IssEntity
import com.spaceapp.data.datasource.local.where_is_the_iss.db.room.dao.IssDao
import javax.inject.Inject

class WhereIsTheIssLocalDataSourceImpl @Inject constructor(private val issDao: IssDao) : WhereIsTheIssLocalDataSource {

    override suspend fun addIss(issEntity: IssEntity) = issDao.addIss(issEntity = issEntity)

    override suspend fun getIss() = issDao.getIss()

    override suspend fun updateIss(issEntity: IssEntity) = issDao.updateIss(issEntity = issEntity)
}