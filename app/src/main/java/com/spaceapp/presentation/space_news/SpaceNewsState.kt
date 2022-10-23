package com.spaceapp.presentation.space_news

import com.spaceapp.domain.model.SpaceNews

sealed interface SpaceNewsState {
    data class Success(val data: List<SpaceNews>?): SpaceNewsState
    data class Error(val errorMessage: String?): SpaceNewsState
    object Loading: SpaceNewsState
}