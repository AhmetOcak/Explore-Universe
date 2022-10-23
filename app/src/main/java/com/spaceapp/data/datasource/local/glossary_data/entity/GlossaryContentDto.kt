package com.spaceapp.data.datasource.local.glossary_data.entity

import com.google.gson.annotations.SerializedName

data class GlossaryContentDto(
    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String
)
