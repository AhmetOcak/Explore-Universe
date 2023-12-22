package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.core.common.Response
import com.spaceapp.domain.model.where_is_the_iss.Iss
import com.spaceapp.domain.repository.WhereIsTheIssRepository
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.NullPointerException
import javax.inject.Inject

class GetIssPositionFromDatabaseUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepository) {

    operator fun invoke(): Flow<Response<Iss>> = flow {
        try {
            emit(Response.Loading)

            val data = issRepository.getIssPositionFromLocal()

            emit(Response.Success(data = data))
        } catch (e: NullPointerException) {
            emit(Response.Error(message = ERROR.INTERNET))
        } catch (e: Exception) {
            emit(Response.Error(message = e.localizedMessage ?: ERROR.UNKNOWN))
        }
    }
}