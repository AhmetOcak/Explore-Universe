package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.WhereIsTheIssRepositoryImpl
import com.spaceapp.domain.model.where_is_the_iss.Iss
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject

class GetIssPositionFromNetworkUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepositoryImpl) {

    operator fun invoke(): Flow<Result<Iss>> = flow {
        try {
            emit(Result.Loading)

            emit(Result.Success(data = issRepository.getIssPositionFromNetwork()))
        } catch (e: IOException) {
            emit(Result.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Result.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}