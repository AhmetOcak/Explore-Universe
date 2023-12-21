package com.spaceapp.domain.model.space_news

data class SpaceNews(
    val status: String,
    val totalResults: Int,
    val articles: List<Articles>
)
