package com.spaceapp.data.repository.nasa

import com.spaceapp.domain.model.apod.Apod
import com.spaceapp.domain.model.mars_photos.MarsPhoto

interface NasaRepository {

    suspend fun addApodToLocal(apod: Apod)

    suspend fun getApodFromNetwork(): List<Apod>

    suspend fun getApodFromLocal(): List<Apod>

    suspend fun deleteLocalApod()

    suspend fun addMarsPhotoToLocal(marsPhoto: MarsPhoto)

    suspend fun getMarsPhotoFromLocal(): List<MarsPhoto>

    suspend fun deleteLocalMars()

    suspend fun getLatestMarsPhotos() : List<MarsPhoto>
}