package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.space_news.db.entity.SpaceNewsEntity
import com.spaceapp.data.datasource.remote.space_news.entity.SpaceNewsDto
import com.spaceapp.domain.model.space_news.Articles
import com.spaceapp.domain.model.space_news.Source
import com.spaceapp.domain.model.space_news.SpaceNews

fun SpaceNewsDto.toSpaceNews(): SpaceNews {
    return SpaceNews(
        articles = articles.map {
            Articles(
                url = it.url ?: "",
                title = it.title ?: "",
                author = it.author ?: "",
                publishedAt = it.publishedAt ?: "",
                source = Source(
                    name = it.source?.name ?: ""
                ),
                urlToImage = it.urlToImage ?: ""
            )
        }
    )
}

fun Articles.toSpaceNewsEntity(): SpaceNewsEntity {
    return SpaceNewsEntity(
        title = title,
        author = author,
        image = urlToImage,
        newsUrl = url,
        date = publishedAt,
        sourceName = source.name
    )
}

fun List<SpaceNewsEntity>.toSpaceNews(): SpaceNews {
    return SpaceNews(
        articles = map {
            Articles(
                source = Source(name = it.sourceName),
                title = it.title,
                url = it.newsUrl,
                publishedAt = it.date,
                urlToImage = it.image,
                author = it.author
            )
        }
    )
}