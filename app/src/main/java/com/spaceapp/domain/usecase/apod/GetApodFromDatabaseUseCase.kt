package com.spaceapp.domain.usecase.apod

import com.spaceapp.domain.model.apod.Apod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.spaceapp.core.common.Result
import com.spaceapp.domain.repository.NasaRepository
import com.spaceapp.domain.utils.ERROR

class GetApodFromDatabaseUseCase @Inject constructor(private val nasaRepository: NasaRepository) {

    operator fun invoke(): Flow<Result<List<Apod>>> = flow {
        try {
            emit(Result.Loading)

            val data = nasaRepository.getApodFromLocal()

            if (data.isNotEmpty()) {
                emit(Result.Success(data = data))
            } else {
                emit(Result.Error(message = ERROR.INTERNET))
            }
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}