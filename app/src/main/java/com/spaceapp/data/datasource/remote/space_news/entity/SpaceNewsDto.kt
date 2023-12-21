package com.spaceapp.data.datasource.remote.space_news.entity

import com.google.gson.annotations.SerializedName

data class SpaceNewsDto(
    @SerializedName("status")
    val status: String,

    @SerializedName("totalResults")
    val totalResults: Int,

    @SerializedName("articles")
    val articles: List<ArticlesDto>
)
