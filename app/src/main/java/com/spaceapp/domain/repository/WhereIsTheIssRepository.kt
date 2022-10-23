package com.spaceapp.domain.repository

import com.spaceapp.domain.model.Iss

interface WhereIsTheIssRepository {

    suspend fun getIssPositionFromNetwork(): Iss

    suspend fun getIssPositionFromLocal(): Iss

    suspend fun addIssPositionToLocal(iss: Iss)

    suspend fun updateIssPosition(iss: Iss)
}