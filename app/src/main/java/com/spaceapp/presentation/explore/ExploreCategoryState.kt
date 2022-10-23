package com.spaceapp.presentation.explore

sealed interface ExploreCategoryState {
    object ALL : ExploreCategoryState
    object COMETS : ExploreCategoryState
    object METEORS : ExploreCategoryState
    object MOONS : ExploreCategoryState
    object PLANETS : ExploreCategoryState
    object STARS: ExploreCategoryState
}