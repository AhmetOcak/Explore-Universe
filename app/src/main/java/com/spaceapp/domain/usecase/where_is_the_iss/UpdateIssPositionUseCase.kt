package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.data.repository.WhereIsTheIssRepositoryImpl
import com.spaceapp.domain.model.where_is_the_iss.Iss
import javax.inject.Inject

class UpdateIssPositionUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepositoryImpl) {

    suspend operator fun invoke(iss: Iss) = issRepository.updateIssPosition(iss = iss)
}