package com.spaceapp.domain.model

data class SpaceNews(
    val title: String,
    val author: String,
    val date: String,
    val image: NewsImage,
    val newsSource: String
)
