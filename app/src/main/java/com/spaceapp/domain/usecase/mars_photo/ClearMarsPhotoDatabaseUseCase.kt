package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.data.repository.MarsPhotosRepositoryImpl
import javax.inject.Inject

class ClearMarsPhotoDatabaseUseCase @Inject constructor(private val marsPhotoRepository: MarsPhotosRepositoryImpl) {

    suspend operator fun invoke() = marsPhotoRepository.deleteLocalMars()
}