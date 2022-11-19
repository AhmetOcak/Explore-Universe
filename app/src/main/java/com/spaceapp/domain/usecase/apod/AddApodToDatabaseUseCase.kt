package com.spaceapp.domain.usecase.apod

import com.spaceapp.domain.model.apod.Apod
import com.spaceapp.domain.repository.ApodRepository
import javax.inject.Inject

class AddApodToDatabaseUseCase @Inject constructor(private val apodRepository: ApodRepository) {

    suspend operator fun invoke(apod: List<Apod>) {
        apod.forEach {
            apodRepository.addApodToLocal(
                apod = Apod(
                    title = it.title,
                    image = it.image
                )
            )
        }
    }
}