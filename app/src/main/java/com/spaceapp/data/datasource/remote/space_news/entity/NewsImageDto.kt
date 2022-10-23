package com.spaceapp.data.datasource.remote.space_news.entity

import com.google.gson.annotations.SerializedName

data class NewsImageDto(
    @SerializedName("src")
    val image: String,
)
