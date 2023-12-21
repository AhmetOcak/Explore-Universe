package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.remote.space_news.entity.SpaceNewsDto
import com.spaceapp.domain.model.space_news.Articles
import com.spaceapp.domain.model.space_news.Source
import com.spaceapp.domain.model.space_news.SpaceNews

fun SpaceNewsDto.toSpaceNews(): SpaceNews {
    return SpaceNews(
        status = status,
        totalResults = totalResults,
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