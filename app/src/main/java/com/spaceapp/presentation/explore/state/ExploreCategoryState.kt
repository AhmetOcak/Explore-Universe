package com.spaceapp.presentation.explore.state

sealed interface ExploreCategoryState {
    data object ALL : ExploreCategoryState
    data object COMETS : ExploreCategoryState
    data object METEORS : ExploreCategoryState
    data object MOONS : ExploreCategoryState
    data object PLANETS : ExploreCategoryState
    data object STARS: ExploreCategoryState
}