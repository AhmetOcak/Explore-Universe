package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.db.entity.SpaceNewsEntity
import com.spaceapp.data.datasource.remote.space_news.entity.NewsImageDto
import com.spaceapp.data.datasource.remote.space_news.entity.SpaceNewsDto
import com.spaceapp.domain.model.NewsImage
import com.spaceapp.domain.model.SpaceNews
import com.spaceapp.presentation.utils.UniverseGlossaryScreenConstants.title

fun List<SpaceNewsDto>.toSpaceNews(): List<SpaceNews> {
    return map {
        SpaceNews(
            title = it.title,
            image = NewsImage(image = it.image.image),
            date = it.date,
            newsSource = it.newsSource,
            author = it.author
        )
    }
}

@JvmName("toSpaceNewsSpaceNewsEntity")
fun List<SpaceNewsEntity>.toSpaceNews(): List<SpaceNews> {
    return map {
        SpaceNews(
            title = it.title,
            image = NewsImage(image = it.image),
            author = it.author,
            date = it.date,
            newsSource = it.newsSource
        )
    }
}

fun SpaceNews.toSpaceNewsEntity(): SpaceNewsEntity {
    return SpaceNewsEntity(
        title = title,
        image = image.image,
        author = author,
        date = date,
        newsSource = newsSource
    )
}