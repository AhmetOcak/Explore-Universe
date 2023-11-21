package com.spaceapp.presentation.explore.state

import com.spaceapp.domain.model.apod.Apod

sealed interface ApodState {
    data class Success(val apodData: List<Apod>?): ApodState
    data class Error(val errorMessage: String?): ApodState
    object Loading: ApodState
}