package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.MarsPhotosRepositoryImpl
import com.spaceapp.domain.model.MarsPhoto
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMarsPhotoFromDatabaseUseCase @Inject constructor(private val marsPhotoRepository: MarsPhotosRepositoryImpl) {

    operator fun invoke(): Flow<Result<List<MarsPhoto>>> = flow {
        try {
            emit(Result.Loading)

            val data = marsPhotoRepository.getMarsPhotoFromLocal()

            if (data.isNotEmpty()) {
                emit(Result.Success(data = data))
            } else {
                emit(Result.Error(message = ERROR.INTERNET))
            }
        } catch (e: Exception) {
            emit(Result.Error(message = e.message ?: "Unknown Error"))
        }
    }
}