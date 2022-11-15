package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.data.repository.MarsPhotosRepositoryImpl
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import javax.inject.Inject

class AddMarsPhotoToDatabaseUseCase @Inject constructor(private val marsPhotoRepository: MarsPhotosRepositoryImpl) {

    suspend operator fun invoke(marsPhoto: MarsPhoto) {
        marsPhoto.photos.forEach {
            marsPhotoRepository.addMarsPhotoToLocal(marsPhoto = MarsPhoto(photos = listOf(it)))
        }
    }
}