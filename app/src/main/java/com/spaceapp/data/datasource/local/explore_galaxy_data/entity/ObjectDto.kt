package com.spaceapp.data.datasource.local.explore_galaxy_data.entity

import com.google.gson.annotations.SerializedName

data class ObjectDto(
    @SerializedName("planets")
    val planets: List<PlanetDto>,

    @SerializedName("moons")
    val moons: List<MoonDto>,

    @SerializedName("comets")
    val comets: List<CometDto>,

    @SerializedName("meteors")
    val meteors: List<MeteorDto>,

    @SerializedName("stars")
    val stars: List<StarDto>,
)
