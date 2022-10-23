package com.spaceapp.presentation.utils

import androidx.annotation.DrawableRes
import com.spaceapp.R

private val names = SpaceObjects

sealed class SpaceObjectImageType(val name: String, @DrawableRes val id: Int) {
    object Halley : SpaceObjectImageType(
        name = names.halley,
        id = R.drawable.halley
    )

    object Oumuamua : SpaceObjectImageType(
        name = names.oumuamua,
        id = R.drawable.oumuamua
    )

    object Wild81p : SpaceObjectImageType(
        name = names.wild81p,
        id = R.drawable.wild_81p
    )

    object Comet103p : SpaceObjectImageType(
        name = names.comet103p,
        id = R.drawable.comet_103p
    )

    object Lenoids : SpaceObjectImageType(
        name = names.lenoids,
        id = R.drawable.lenoids
    )

    object Lyrids : SpaceObjectImageType(
        name = names.lyrids,
        id = R.drawable.lyrids
    )

    object Orinoids : SpaceObjectImageType(
        name = names.orinoids,
        id = R.drawable.orinoids
    )

    object Perseids : SpaceObjectImageType(
        name = names.perseids,
        id = R.drawable.perseids
    )

    object Encaladus : SpaceObjectImageType(
        name = names.encaladus,
        id = R.drawable.encaladus
    )

    object Europa : SpaceObjectImageType(
        name = names.europa,
        id = R.drawable.europa
    )

    object Moon : SpaceObjectImageType(
        name = names.moon,
        id = R.drawable.moon
    )

    object Phobo : SpaceObjectImageType(
        name = names.phobo,
        id = R.drawable.phobo
    )

    object Titan : SpaceObjectImageType(
        name = names.titan,
        id = R.drawable.titan
    )

    object Earth : SpaceObjectImageType(
        name = names.earth,
        id = R.drawable.earth
    )

    object Jupiter : SpaceObjectImageType(
        name = names.jupiter,
        id = R.drawable.jupiter
    )

    object Mars : SpaceObjectImageType(
        name = names.mars,
        id = R.drawable.mars
    )

    object Mercury : SpaceObjectImageType(
        name = names.mercury,
        id = R.drawable.mercury
    )

    object Pluto : SpaceObjectImageType(
        name = names.pluto,
        id = R.drawable.pluto
    )

    object Saturn : SpaceObjectImageType(
        name = names.saturn,
        id = R.drawable.saturn
    )

    object Uranus : SpaceObjectImageType(
        name = names.uranus,
        id = R.drawable.uranus
    )

    object Venus : SpaceObjectImageType(
        name = names.venus,
        id = R.drawable.venus
    )

    object Neptune : SpaceObjectImageType(
        name = names.neptune,
        id = R.drawable.neptune
    )

    object AlphaCentauri : SpaceObjectImageType(
        name = names.alphaCentauri,
        id = R.drawable.alpha_centauri
    )

    object Antares : SpaceObjectImageType(
        name = names.antares,
        id = R.drawable.antares
    )

    object Betelgeuse : SpaceObjectImageType(
        name = names.betelgeuse,
        id = R.drawable.betelgeuse
    )

    object Canopus : SpaceObjectImageType(
        name = names.canopus,
        id = R.drawable.canopus
    )

    object Pleiades : SpaceObjectImageType(
        name = names.pleiades,
        id = R.drawable.pleiades
    )

    object Polaris : SpaceObjectImageType(
        name = names.polaris,
        id = R.drawable.polaris
    )

    object Rigel : SpaceObjectImageType(
        name = names.rigel,
        id = R.drawable.rigel
    )

    object Sirius : SpaceObjectImageType(
        name = names.sirius,
        id = R.drawable.sirius
    )

    object Vega : SpaceObjectImageType(
        name = names.vega,
        id = R.drawable.vega
    )

    companion object {
        fun setSpaceObjectImageType(spaceObjectName: String): Int {
            return when (spaceObjectName) {
                Comet103p.name -> {
                    Comet103p.id
                }
                Halley.name -> {
                    Halley.id
                }
                Oumuamua.name -> {
                    Oumuamua.id
                }
                Wild81p.name -> {
                    Wild81p.id
                }
                Lenoids.name -> {
                    Lenoids.id
                }
                Lyrids.name -> {
                    Lyrids.id
                }
                Orinoids.name -> {
                    Orinoids.id
                }
                Perseids.name -> {
                    Perseids.id
                }
                Encaladus.name -> {
                    Encaladus.id
                }
                Europa.name -> {
                    Europa.id
                }
                Moon.name -> {
                    Moon.id
                }
                Phobo.name -> {
                    Phobo.id
                }
                Titan.name -> {
                    Titan.id
                }
                Earth.name -> {
                    Earth.id
                }
                Jupiter.name -> {
                    Jupiter.id
                }
                Neptune.name -> {
                    Neptune.id
                }
                Mars.name -> {
                    Mars.id
                }
                Mercury.name -> {
                    Mercury.id
                }
                Pluto.name -> {
                    Pluto.id
                }
                Saturn.name -> {
                    Saturn.id
                }
                Uranus.name -> {
                    Uranus.id
                }
                Venus.name -> {
                    Venus.id
                }
                AlphaCentauri.name -> {
                    AlphaCentauri.id
                }
                Antares.name -> {
                    Antares.id
                }
                Betelgeuse.name -> {
                    Betelgeuse.id
                }
                Canopus.name -> {
                    Canopus.id
                }
                Pleiades.name -> {
                    Pleiades.id
                }
                Polaris.name -> {
                    Polaris.id
                }
                Rigel.name -> {
                    Rigel.id
                }
                Sirius.name -> {
                    Sirius.id
                }
                else -> {
                    Vega.id
                }
            }
        }
    }
}
