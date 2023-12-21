package com.spaceapp.domain.model.space_news

data class Articles(
    val source: Source,
    val author: String,
    val title: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
)
