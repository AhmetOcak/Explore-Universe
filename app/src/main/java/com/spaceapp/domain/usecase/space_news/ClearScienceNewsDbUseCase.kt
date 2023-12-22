package com.spaceapp.domain.usecase.space_news

import com.spaceapp.domain.repository.SpaceNewsRepository
import javax.inject.Inject

class ClearScienceNewsDbUseCase @Inject constructor(private val repository: SpaceNewsRepository) {

    suspend operator fun invoke() = repository.deleteAllScienceNews()
}