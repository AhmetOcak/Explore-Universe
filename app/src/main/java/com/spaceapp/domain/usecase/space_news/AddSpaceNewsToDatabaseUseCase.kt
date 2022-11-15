package com.spaceapp.domain.usecase.space_news

import com.spaceapp.data.repository.SpaceNewsRepositoryImpl
import com.spaceapp.domain.model.space_news.NewsImage
import com.spaceapp.domain.model.space_news.SpaceNews
import javax.inject.Inject

class AddSpaceNewsToDatabaseUseCase @Inject constructor(private val spaceNewsRepository: SpaceNewsRepositoryImpl) {

    suspend operator fun invoke(spaceNews: List<SpaceNews>) {
        spaceNews.forEach {
            spaceNewsRepository.addSpaceToLocal(
                spaceNews = SpaceNews(
                    title = it.title,
                    newsSource = it.newsSource,
                    date = it.date,
                    image = NewsImage(
                        image = it.image.image
                    ),
                    author = it.author
                )
            )
        }
    }
}