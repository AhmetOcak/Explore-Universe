package com.spaceapp.presentation.explore_detail

/*
    GROUP1 -> PLANETS
    GROUP2 -> MOONS
    GROUP3 -> METEORS
    GROUP4 -> STARS
    GROUP5 -> COMETS
 */

sealed interface ObjectGroupsState {
    object Group1 : ObjectGroupsState
    object Group2 : ObjectGroupsState
    object Group3 : ObjectGroupsState
    object Group4 : ObjectGroupsState
    object Group5 : ObjectGroupsState
}