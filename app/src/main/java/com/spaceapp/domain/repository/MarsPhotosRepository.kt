package com.spaceapp.domain.repository

import com.spaceapp.domain.model.MarsPhoto

interface MarsPhotosRepository {

    suspend fun addMarsPhotoToLocal(marsPhoto: MarsPhoto)

    suspend fun getMarsPhotoFromLocal(): List<MarsPhoto>

    suspend fun deleteLocalMars()

    suspend fun getLatestMarsPhotos() : List<MarsPhoto>
}