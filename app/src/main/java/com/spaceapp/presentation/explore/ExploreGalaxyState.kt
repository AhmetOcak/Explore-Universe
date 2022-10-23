package com.spaceapp.presentation.explore

import com.spaceapp.domain.model.SpaceObject

sealed interface ExploreGalaxyState {
    data class Success(val data : SpaceObject?) : ExploreGalaxyState
    data class Error(val errorMessage : String?) : ExploreGalaxyState
    object Loading : ExploreGalaxyState
}