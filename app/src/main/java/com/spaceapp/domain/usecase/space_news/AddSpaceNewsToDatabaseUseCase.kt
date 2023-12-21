package com.spaceapp.domain.usecase.space_news


import com.spaceapp.data.repository.space_news.SpaceNewsRepository
import javax.inject.Inject

class AddSpaceNewsToDatabaseUseCase @Inject constructor(private val spaceNewsRepository: SpaceNewsRepository) {

/*    suspend operator fun invoke(spaceNews: List<SpaceNews>) {
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
    }*/
}