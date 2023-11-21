package com.spaceapp.presentation.home.state

import com.spaceapp.domain.model.mars_photos.MarsPhoto

sealed interface MarsPhotoState {
    data class Success(val data: List<MarsPhoto>?) : MarsPhotoState
    data class Error(val errorMessage: String?) : MarsPhotoState
    object Loading : MarsPhotoState
}