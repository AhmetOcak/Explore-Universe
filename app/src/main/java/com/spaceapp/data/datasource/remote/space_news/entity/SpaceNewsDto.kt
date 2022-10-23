package com.spaceapp.data.datasource.remote.space_news.entity

import com.google.gson.annotations.SerializedName

data class SpaceNewsDto(

    @SerializedName("title")
    val title: String,

    @SerializedName("author")
    val author: String,

    @SerializedName("time")
    val date: String,

    @SerializedName("image")
    val image: NewsImageDto,

    @SerializedName("link")
    val newsSource: String
)
