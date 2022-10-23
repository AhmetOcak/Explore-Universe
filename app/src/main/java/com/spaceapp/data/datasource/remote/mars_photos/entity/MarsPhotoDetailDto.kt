package com.spaceapp.data.datasource.remote.mars_photos.entity

import com.google.gson.annotations.SerializedName

data class MarsPhotoDetailDto(
    @SerializedName("img_src")
    val image: String,

    @SerializedName("earth_date")
    val date: String,

    @SerializedName("rover")
    val rover: RoverDto
)
