package com.spaceapp.data.datasource.remote.space_news.entity

import com.google.gson.annotations.SerializedName

data class SourceDto(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String
)
