package com.spaceapp.data.datasource.remote.mars_photos.entity

import com.google.gson.annotations.SerializedName

data class MarsPhotoDto(
    @SerializedName("latest_photos")
    val photos: List<MarsPhotoDetailDto>
)
