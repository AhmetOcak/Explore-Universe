package com.spaceapp.presentation.utils

import androidx.annotation.DrawableRes
import com.spaceapp.R

private val names = GlossaryNames

sealed class GlossaryImageType(
    val glossaryName: String,
    @DrawableRes val id: Int
) {
    object Asteroid : GlossaryImageType(
        glossaryName = names.asteroid,
        id = R.drawable.asteroid
    )

    object Atmosphere : GlossaryImageType(
        glossaryName = names.atmosphere,
        id = R.drawable.atmosphere
    )

    object Atom : GlossaryImageType(
        glossaryName = names.atom,
        id = R.drawable.atom
    )

    object Aurora : GlossaryImageType(
        glossaryName = names.aurora,
        id = R.drawable.auro
    )

    object BlackHole : GlossaryImageType(
        glossaryName = names.black_hole,
        id = R.drawable.black_hole
    )

    object Comet : GlossaryImageType(
        glossaryName = names.comet,
        id = R.drawable.comet
    )

    object Cosmos : GlossaryImageType(
        glossaryName = names.cosmos,
        id = R.drawable.cosmos
    )

    object Constellation : GlossaryImageType(
        glossaryName = names.constellation,
        id = R.drawable.constellation
    )

    object Corona : GlossaryImageType(
        glossaryName = names.corona,
        id = R.drawable.corona
    )

    object Crater : GlossaryImageType(
        glossaryName = names.crater,
        id = R.drawable.crater
    )

    object DwarfPlanet : GlossaryImageType(
        glossaryName = names.dwarf_planet,
        id = R.drawable.dwarf_planet
    )

    object ElNino : GlossaryImageType(
        glossaryName = names.el_nino,
        id = R.drawable.el_nino
    )

    object ElectromagneticSpectrum : GlossaryImageType(
        glossaryName = names.electromagnetic_spectrum,
        id = R.drawable.electromagnetic_spectrum
    )

    object Galaxy : GlossaryImageType(
        glossaryName = names.galaxy,
        id = R.drawable.galaxy
    )

    object GammaRays : GlossaryImageType(
        glossaryName = names.gamma_rays,
        id = R.drawable.gamma_rays
    )

    object GPS : GlossaryImageType(
        glossaryName = names.gps,
        id = R.drawable.gps
    )

    object Gravity : GlossaryImageType(
        glossaryName = names.gravity,
        id = R.drawable.gravity
    )

    object MagneticField : GlossaryImageType(
        glossaryName = names.magnetic_field,
        id = R.drawable.magnetic_field
    )

    object Meteor : GlossaryImageType(
        glossaryName = names.meteor,
        id = R.drawable.meteor
    )

    object Meteorite : GlossaryImageType(
        glossaryName = names.meteorite,
        id = R.drawable.meteorite
    )

    object Molecule : GlossaryImageType(
        glossaryName = names.molecule,
        id = R.drawable.molecule
    )

    object Moon : GlossaryImageType(
        glossaryName = names.moon,
        id = R.drawable.moon_glossary
    )

    object Nebula : GlossaryImageType(
        glossaryName = names.nebula,
        id = R.drawable.nebula
    )

    object NeutronStar : GlossaryImageType(
        glossaryName = names.neutron_star,
        id = R.drawable.neutron_star
    )

    object Orbit : GlossaryImageType(
        glossaryName = names.orbit,
        id = R.drawable.orbit
    )

    object Radiation : GlossaryImageType(
        glossaryName = names.radiation,
        id = R.drawable.radiation
    )

    object Wave : GlossaryImageType(
        glossaryName = names.wave,
        id = R.drawable.wave
    )

    object XRays : GlossaryImageType(
        glossaryName = names.x_rays,
        id = R.drawable.x_ray
    )

    companion object {
        fun setGlossaryImageType(name: String): Int {
            return when (name) {
                Asteroid.glossaryName -> {
                    Asteroid.id
                }
                Atmosphere.glossaryName -> {
                    Atmosphere.id
                }
                Atom.glossaryName -> {
                    Atom.id
                }
                Aurora.glossaryName -> {
                    Aurora.id
                }
                BlackHole.glossaryName -> {
                    BlackHole.id
                }
                Comet.glossaryName -> {
                    Comet.id
                }
                Constellation.glossaryName -> {
                    Constellation.id
                }
                Corona.glossaryName -> {
                    Corona.id
                }
                Cosmos.glossaryName -> {
                    Cosmos.id
                }
                Crater.glossaryName -> {
                    Crater.id
                }
                DwarfPlanet.glossaryName -> {
                    DwarfPlanet.id
                }
                ElNino.glossaryName -> {
                    ElNino.id
                }
                ElectromagneticSpectrum.glossaryName -> {
                    ElectromagneticSpectrum.id
                }
                Galaxy.glossaryName -> {
                    Galaxy.id
                }
                GammaRays.glossaryName -> {
                    GammaRays.id
                }
                GPS.glossaryName -> {
                    GPS.id
                }
                Gravity.glossaryName -> {
                    Gravity.id
                }
                MagneticField.glossaryName -> {
                    MagneticField.id
                }
                Meteor.glossaryName -> {
                    Meteor.id
                }
                Meteorite.glossaryName -> {
                    Meteorite.id
                }
                Molecule.glossaryName -> {
                    Molecule.id
                }
                Moon.glossaryName -> {
                    Moon.id
                }
                Nebula.glossaryName -> {
                    Nebula.id
                }
                NeutronStar.glossaryName -> {
                    NeutronStar.id
                }
                Orbit.glossaryName -> {
                    Orbit.id
                }
                Radiation.glossaryName -> {
                    Radiation.id
                }
                Wave.glossaryName -> {
                    Wave.id
                }
                else -> {
                    MagneticField.id
                }
            }
        }
    }
}
