package com.spaceapp.presentation.space_news.state

import com.spaceapp.domain.model.space_news.SpaceNews

sealed interface SpaceNewsState {
    data class Success(val data: List<SpaceNews>?): SpaceNewsState
    data class Error(val errorMessage: String?): SpaceNewsState
    object Loading: SpaceNewsState
}