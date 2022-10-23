package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.WhereIsTheIssRepositoryImpl
import com.spaceapp.domain.model.Iss
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.NullPointerException
import javax.inject.Inject

class GetIssPositionFromDatabaseUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepositoryImpl) {

    operator fun invoke(): Flow<Result<Iss>> = flow {
        try {
            emit(Result.Loading)

            val data = issRepository.getIssPositionFromLocal()

            emit(Result.Success(data = data))
        } catch (e: NullPointerException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}