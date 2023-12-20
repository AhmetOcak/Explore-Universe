package com.spaceapp.domain.usecase.apod

import android.util.Log
import com.spaceapp.domain.repository.NasaRepository
import javax.inject.Inject

class ClearApodDatabaseUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    suspend operator fun invoke() {
        try {
            nasaRepository.deleteLocalApod()
        }catch (e: Exception) {
            Log.e("apod delete", e.message.toString())
        }
    }
}