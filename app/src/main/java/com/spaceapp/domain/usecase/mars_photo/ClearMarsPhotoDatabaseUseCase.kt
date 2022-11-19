package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.domain.repository.MarsPhotosRepository
import javax.inject.Inject

class ClearMarsPhotoDatabaseUseCase @Inject constructor(private val marsPhotoRepository: MarsPhotosRepository) {

    suspend operator fun invoke() = marsPhotoRepository.deleteLocalMars()
}