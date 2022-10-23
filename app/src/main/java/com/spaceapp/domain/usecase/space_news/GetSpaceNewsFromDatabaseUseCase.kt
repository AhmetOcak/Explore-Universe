package com.spaceapp.domain.usecase.space_news

import com.spaceapp.data.repository.SpaceNewsRepositoryImpl
import com.spaceapp.domain.model.SpaceNews
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.spaceapp.core.common.Result
import com.spaceapp.domain.utils.ERROR

class GetSpaceNewsFromDatabaseUseCase @Inject constructor(private val spaceNewsRepository: SpaceNewsRepositoryImpl) {

    suspend operator fun invoke(): Flow<Result<List<SpaceNews>>> = flow {
        try {
            emit(Result.Loading)

            val data = spaceNewsRepository.getSpaceNewsFromLocal()

            if (data.isNotEmpty()) {
                emit(Result.Success(data))
            } else {
                emit(Result.Error(message = ERROR.INTERNET))
            }
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}