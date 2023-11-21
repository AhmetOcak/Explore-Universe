package com.spaceapp.presentation.explore_detail.state

sealed interface CategoryState {
    object Overview : CategoryState
    object Information : CategoryState
}
