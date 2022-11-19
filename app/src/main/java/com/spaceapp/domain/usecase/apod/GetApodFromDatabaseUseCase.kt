package com.spaceapp.domain.usecase.apod

import com.spaceapp.data.repository.ApodRepositoryImpl
import com.spaceapp.domain.model.apod.Apod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.spaceapp.core.common.Result
import com.spaceapp.domain.repository.ApodRepository
import com.spaceapp.domain.utils.ERROR

class GetApodFromDatabaseUseCase @Inject constructor(private val apodRepository: ApodRepository) {

    operator fun invoke(): Flow<Result<List<Apod>>> = flow {
        try {
            emit(Result.Loading)

            val data = apodRepository.getApodFromLocal()

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