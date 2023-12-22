package com.spaceapp.domain.usecase.space_news

import com.spaceapp.domain.repository.SpaceNewsRepository
import com.spaceapp.domain.model.space_news.SpaceNews
import javax.inject.Inject

class AddScienceNewsToDatabaseUseCase @Inject constructor(private val repository: SpaceNewsRepository) {

    suspend operator fun invoke(spaceNews: SpaceNews) {
        repository.addScienceNews(spaceNews)
    }
}