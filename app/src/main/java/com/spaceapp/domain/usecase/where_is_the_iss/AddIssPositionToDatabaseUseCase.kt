package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.domain.model.where_is_the_iss.Iss
import com.spaceapp.data.repository.iss.WhereIsTheIssRepository
import javax.inject.Inject

class AddIssPositionToDatabaseUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepository) {

    suspend operator fun invoke(iss: Iss) = issRepository.addIssPositionToLocal(iss = iss)
}