package com.spaceapp.data.datasource.local.db

import com.spaceapp.data.datasource.local.db.entity.IssEntity
import com.spaceapp.data.datasource.local.db.room.dao.IssDao
import javax.inject.Inject

class WhereIsTheIssLocalDataSource @Inject constructor(private val issDao: IssDao) {

    suspend fun addIss(issEntity: IssEntity) = issDao.addIss(issEntity = issEntity)

    suspend fun getIss() = issDao.getIss()

    suspend fun updateIss(issEntity: IssEntity) = issDao.updateIss(issEntity = issEntity)
}