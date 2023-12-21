package com.spaceapp.data.datasource.remote.space_news.entity

import com.google.gson.annotations.SerializedName

data class ArticlesDto(
    @SerializedName("source")
    val source: SourceDto?,

    @SerializedName("author")
    val author: String?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("content") val
    content: String?
)

