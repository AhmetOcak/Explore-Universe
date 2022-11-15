package com.spaceapp.domain.model.explore_galaxy_data

data class Object(
    val planets: List<Planet>,
    val moons: List<Moon>,
    val comets: List<Comet>,
    val meteors: List<Meteor>,
    val stars: List<Star>,
)
