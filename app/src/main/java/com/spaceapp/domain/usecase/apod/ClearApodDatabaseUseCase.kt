package com.spaceapp.domain.usecase.apod

import android.util.Log
import com.spaceapp.domain.repository.ApodRepository
import javax.inject.Inject

class ClearApodDatabaseUseCase @Inject constructor(private val apodRepository: ApodRepository) {

    suspend operator fun invoke() {
        try {
            apodRepository.deleteLocalApod()
        }catch (e: Exception) {
            Log.e("apod delete", e.message.toString())
        }
    }
}