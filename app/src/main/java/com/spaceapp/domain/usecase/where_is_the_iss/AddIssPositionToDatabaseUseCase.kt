package com.spaceapp.domain.usecase.where_is_the_iss

import com.spaceapp.data.repository.WhereIsTheIssRepositoryImpl
import com.spaceapp.domain.model.Iss
import javax.inject.Inject

class AddIssPositionToDatabaseUseCase @Inject constructor(private val issRepository: WhereIsTheIssRepositoryImpl) {

    suspend operator fun invoke(iss: Iss) = issRepository.addIssPositionToLocal(iss = iss)
}