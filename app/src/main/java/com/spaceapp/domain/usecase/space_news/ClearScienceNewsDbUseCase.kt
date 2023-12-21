package com.spaceapp.domain.usecase.space_news

import com.spaceapp.data.repository.space_news.SpaceNewsRepository
import javax.inject.Inject

class ClearScienceNewsDbUseCase @Inject constructor(private val repository: SpaceNewsRepository) {

    suspend operator fun invoke() = repository.deleteAllScienceNews()
}