package com.spaceapp.domain.usecase.space_news

import com.spaceapp.data.repository.space_news.SpaceNewsRepository
import javax.inject.Inject

class ClearSpaceNewsDatabaseUseCase @Inject constructor(private val spaceNewsRepository: SpaceNewsRepository) {

    suspend operator fun invoke() = spaceNewsRepository.deleteLocalSpaceNews()
}