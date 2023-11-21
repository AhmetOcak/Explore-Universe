package com.spaceapp.presentation.home.state

import com.spaceapp.domain.model.where_is_the_iss.Iss

sealed interface WhereIsTheIssState {
    data class Success(val data: Iss?) : WhereIsTheIssState
    data class Error(val errorMessage: String?) : WhereIsTheIssState
    object Loading : WhereIsTheIssState
}