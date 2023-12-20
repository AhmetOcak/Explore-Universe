package com.spaceapp.domain.usecase.apod

import com.spaceapp.domain.model.apod.Apod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.spaceapp.core.common.Result
import com.spaceapp.domain.repository.NasaRepository
import okio.IOException

class GetApodFromNetworkUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    operator fun invoke(): Flow<Result<List<Apod>>> = flow {
        try {
            emit(Result.Loading)

            emit(Result.Success(nasaRepository.getApodFromNetwork()))
        } catch (e: IOException) {
            emit(Result.Error(message = "Please check your internet connection."))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: "Unknown Error"))
        }
    }
}