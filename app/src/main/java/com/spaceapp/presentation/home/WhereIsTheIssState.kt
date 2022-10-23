package com.spaceapp.presentation.home

import com.spaceapp.domain.model.Iss

sealed interface WhereIsTheIssState {
    data class Success(val data: Iss?) : WhereIsTheIssState
    data class Error(val errorMessage: String?) : WhereIsTheIssState
    object Loading : WhereIsTheIssState
}