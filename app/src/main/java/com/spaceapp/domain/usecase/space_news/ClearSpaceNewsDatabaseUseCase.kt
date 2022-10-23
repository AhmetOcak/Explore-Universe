package com.spaceapp.domain.usecase.space_news

import com.spaceapp.data.repository.SpaceNewsRepositoryImpl
import javax.inject.Inject

class ClearSpaceNewsDatabaseUseCase @Inject constructor(private val spaceNewsRepository: SpaceNewsRepositoryImpl) {

    suspend operator fun invoke() = spaceNewsRepository.deleteLocalSpaceNews()
}