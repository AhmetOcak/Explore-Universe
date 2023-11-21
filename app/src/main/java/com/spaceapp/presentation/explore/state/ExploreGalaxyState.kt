package com.spaceapp.presentation.explore.state

import com.spaceapp.domain.model.explore_galaxy_data.SpaceObject

sealed interface ExploreGalaxyState {
    data class Success(val data : SpaceObject?) : ExploreGalaxyState
    data class Error(val errorMessage : String?) : ExploreGalaxyState
    object Loading : ExploreGalaxyState
}