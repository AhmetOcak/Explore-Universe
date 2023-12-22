package com.spaceapp.domain.usecase.apod

import com.spaceapp.domain.model.apod.Apod
import com.spaceapp.domain.repository.NasaRepository
import javax.inject.Inject

class AddApodToDatabaseUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    suspend operator fun invoke(apod: List<Apod>) {
        apod.forEach {
            nasaRepository.addApodToLocal(
                apod = Apod(
                    title = it.title,
                    image = it.image
                )
            )
        }
    }
}