package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.data.repository.nasa.NasaRepository
import javax.inject.Inject

class ClearMarsPhotoDatabaseUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    suspend operator fun invoke() = nasaRepository.deleteLocalMars()
}