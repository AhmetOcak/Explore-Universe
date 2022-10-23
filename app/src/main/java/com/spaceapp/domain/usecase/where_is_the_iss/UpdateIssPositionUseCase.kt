package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.core.common.Result
import com.spaceapp.data.repository.WhereIsTheIssRepositoryImpl
import com.spaceapp.domain.model.Iss
import com.spaceapp.domain.utils.ERROR
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import javax.inject.Inject

class UpdateIssPositionUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepositoryImpl) {

    suspend operator fun invoke(iss: Iss) = issRepository.updateIssPosition(iss = iss)
}