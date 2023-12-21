package com.spaceapp.presentation.space_news.state

import com.spaceapp.domain.model.space_news.Articles

sealed interface SpaceNewsState {
    data class Success(val data: List<Articles>): SpaceNewsState
    data class Error(val errorMessage: String): SpaceNewsState
    data object Loading: SpaceNewsState
}