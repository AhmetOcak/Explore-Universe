package com.spaceapp.domain.repository

import com.spaceapp.domain.model.apod.Apod

interface ApodRepository {

    suspend fun addApodToLocal(apod: Apod)

    suspend fun getApodFromNetwork(): List<Apod>

    suspend fun getApodFromLocal(): List<Apod>

    suspend fun deleteLocalApod()
}