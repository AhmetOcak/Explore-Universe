package com.spaceapp.domain.model

data class Object(
    val planets: List<Planet>,
    val moons: List<Moon>,
    val comets: List<Comet>,
    val meteors: List<Meteor>,
    val stars: List<Star>,
)
