package com.spaceapp.data.datasource.local.glossary_data.entity

import com.google.gson.annotations.SerializedName

data class GlossaryDto(
    @SerializedName("glossary")
    val glossary: List<GlossaryContentDto>
)
