package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.domain.model.where_is_the_iss.Iss
import com.spaceapp.domain.repository.WhereIsTheIssRepository
import javax.inject.Inject

class UpdateIssPositionUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepository) {

    suspend operator fun invoke(iss: Iss) = issRepository.updateIssPosition(iss = iss)
}