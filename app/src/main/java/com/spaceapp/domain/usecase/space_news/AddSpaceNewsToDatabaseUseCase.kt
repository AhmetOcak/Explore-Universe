package com.spaceapp.domain.usecase.space_news

import com.spaceapp.domain.repository.SpaceNewsRepository
import com.spaceapp.domain.model.space_news.SpaceNews
import javax.inject.Inject

class AddSpaceNewsToDatabaseUseCase @Inject constructor(private val spaceNewsRepository: SpaceNewsRepository) {

    suspend operator fun invoke(spaceNews: SpaceNews) {
        spaceNewsRepository.addSpaceToLocal(spaceNews)
    }
}