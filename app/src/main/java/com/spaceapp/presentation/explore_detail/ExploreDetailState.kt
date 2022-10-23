package com.spaceapp.presentation.explore_detail

sealed interface ExploreDetailState {
    data class Success(val data: Any) : ExploreDetailState
    data class Error(val errorMessage: String) : ExploreDetailState
    object Loading : ExploreDetailState
}