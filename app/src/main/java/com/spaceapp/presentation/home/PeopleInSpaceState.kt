package com.spaceapp.presentation.home

import com.spaceapp.domain.model.PeopleInSpace

sealed interface PeopleInSpaceState {
    data class Success(val data: List<PeopleInSpace>?) : PeopleInSpaceState
    data class Error(val errorMessage: String?) : PeopleInSpaceState
    object Loading : PeopleInSpaceState
}