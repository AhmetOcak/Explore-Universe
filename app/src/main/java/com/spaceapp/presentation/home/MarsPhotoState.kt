package com.spaceapp.presentation.home

import com.spaceapp.domain.model.MarsPhoto

sealed interface MarsPhotoState {
    data class Success(val data: List<MarsPhoto>?) : MarsPhotoState
    data class Error(val errorMessage: String?) : MarsPhotoState
    object Loading : MarsPhotoState
}