package com.spaceapp.domain.usecase.apod

import com.spaceapp.data.repository.ApodRepositoryImpl
import com.spaceapp.domain.model.apod.Apod
import javax.inject.Inject

class AddApodToDatabaseUseCase @Inject constructor(private val apodRepository: ApodRepositoryImpl) {

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