package com.spaceapp.domain.usecase.mars_photo

import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.MarsPhotosRepositoryImpl
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class GetLatestMarsPhotoFromNetworkUseCase @Inject constructor(private val marsPhotoRepository: MarsPhotosRepositoryImpl) {

    operator fun invoke(): Flow<Result<List<MarsPhoto>>> = flow {
        try {
            emit(Result.Loading)

            emit(Result.Success(data = marsPhotoRepository.getLatestMarsPhotos()))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}