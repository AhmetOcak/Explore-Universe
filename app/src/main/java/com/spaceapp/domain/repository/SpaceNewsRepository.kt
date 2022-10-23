package com.spaceapp.domain.repository

import com.spaceapp.domain.model.SpaceNews

interface SpaceNewsRepository {

    suspend fun addSpaceToLocal(spaceNews: SpaceNews)

    suspend fun getSpaceNewsFromLocal(): List<SpaceNews>

    suspend fun deleteLocalSpaceNews()

    suspend fun getSpaceNewsFromNetwork(): List<SpaceNews>
}