package com.spaceapp.presentation.home.state

import com.spaceapp.domain.model.people_in_space.PeopleInSpace

sealed interface PeopleInSpaceState {
    data class Success(val data: List<PeopleInSpace>?) : PeopleInSpaceState
    data class Error(val errorMessage: String?) : PeopleInSpaceState
    object Loading : PeopleInSpaceState
}