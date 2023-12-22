package com.spaceapp.presentation.space_news.state

import com.spaceapp.domain.model.space_news.Articles

sealed interface ScienceNewsState {
    data class Success(val data: List<Articles>): ScienceNewsState
    data class Error(val errorMessage: String): ScienceNewsState
    data object Loading: ScienceNewsState
    data object Nothing: ScienceNewsState
}