package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.core.common.Response
import com.spaceapp.core.common.helper.call
import com.spaceapp.domain.model.where_is_the_iss.Iss
import com.spaceapp.domain.repository.WhereIsTheIssRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetIssPositionFromNetworkUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepository) {

    operator fun invoke(): Flow<Response<Iss>> = call {
        issRepository.getIssPositionFromNetwork()
    }
}