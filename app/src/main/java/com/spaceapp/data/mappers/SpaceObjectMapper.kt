package com.spaceapp.data.mappers

import com.spaceapp.data.datasource.local.explore_galaxy_data.entity.SpaceObjectDto
import com.spaceapp.domain.model.*

fun SpaceObjectDto.toSpaceObject(): SpaceObject {
    return SpaceObject(
        spaceObjects = spaceObjects.map {
            Object(
                comets = it.comets.map { cometDto ->
                    Comet(
                        cometName = cometDto.cometName,
                        cometRadius = cometDto.cometRadius,
                        cometDescription = cometDto.cometDescription
                    )
                },
                meteors = it.meteors.map { meteorDto ->
                    Meteor(
                        meteorName = meteorDto.meteorName,
                        meteorDescription = meteorDto.meteorDescription,
                        meteorVelocity = meteorDto.meteorVelocity
                    )
                },
                moons = it.moons.map { moonDto ->
                    Moon(
                        moonName = moonDto.moonName,
                        moonRadius = moonDto.moonRadius,
                        moonDescription = moonDto.moonDescription,
                        distanceFromSun = moonDto.distanceFromSun
                    )
                },
                planets = it.planets.map { planetDto ->
                    Planet(
                        planetName = planetDto.planetName,
                        planetRadius = planetDto.planetRadius,
                        planetDescription = planetDto.planetDescription,
                        distanceFromSun = planetDto.distanceFromSun
                    )
                },
                stars = it.stars.map { starDto ->
                    Star(
                        starName = starDto.starName,
                        starDescription = starDto.starDescription
                    )
                }
            )
        }
    )
}