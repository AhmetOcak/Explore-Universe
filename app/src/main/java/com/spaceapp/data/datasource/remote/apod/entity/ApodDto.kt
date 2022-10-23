package com.spaceapp.data.datasource.remote.apod.entity

import com.google.gson.annotations.SerializedName

data class ApodDto(
    @SerializedName("title")
    val title: String,

    @SerializedName("url")
    val image: String
)
