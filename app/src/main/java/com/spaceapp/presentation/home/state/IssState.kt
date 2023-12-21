package com.spaceapp.presentation.home.state

import com.spaceapp.domain.model.where_is_the_iss.Iss

sealed interface IssState {
    data class Success(val data: Iss?) : IssState
    data class Error(val errorMessage: String?) : IssState
    data object Loading : IssState
}