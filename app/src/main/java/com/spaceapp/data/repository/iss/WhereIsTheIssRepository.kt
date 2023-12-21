package com.spaceapp.data.repository.iss

import com.spaceapp.domain.model.where_is_the_iss.Iss

interface WhereIsTheIssRepository {

    suspend fun getIssPositionFromNetwork(): Iss

    suspend fun getIssPositionFromLocal(): Iss

    suspend fun addIssPositionToLocal(iss: Iss)

    suspend fun updateIssPosition(iss: Iss)
}