package com.spaceapp.data.datasource.remote.people_in_space.entity

import com.google.gson.annotations.SerializedName

data class PeopleInSpaceDto(
    @SerializedName("people")
    val people: List<DetailDto>
)
