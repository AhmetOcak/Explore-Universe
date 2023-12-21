package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.domain.model.mars_photos.MarsPhoto
import com.spaceapp.data.repository.nasa.NasaRepository
import javax.inject.Inject

class AddMarsPhotoToDatabaseUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    suspend operator fun invoke(marsPhoto: MarsPhoto) {
        marsPhoto.photos.forEach {
            nasaRepository.addMarsPhotoToLocal(marsPhoto = MarsPhoto(photos = listOf(it)))
        }
    }
}