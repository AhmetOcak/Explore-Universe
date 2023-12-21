package com.spaceapp.domain.usecase.space_news

import com.spaceapp.data.repository.space_news.SpaceNewsRepository
import com.spaceapp.domain.model.space_news.SpaceNews
import javax.inject.Inject

class AddScienceNewsToDatabaseUseCase @Inject constructor(private val repository: SpaceNewsRepository) {

    suspend operator fun invoke(spaceNews: SpaceNews) {
        repository.addScienceNews(spaceNews)
    }
}