package com.spaceapp.data.datasource.local.where_is_the_iss

import com.spaceapp.data.datasource.local.where_is_the_iss.db.entity.IssEntity

interface WhereIsTheIssLocalDataSource {

    suspend fun addIss(issEntity: IssEntity)

    suspend fun getIss() : IssEntity

    suspend fun updateIss(issEntity: IssEntity)
}