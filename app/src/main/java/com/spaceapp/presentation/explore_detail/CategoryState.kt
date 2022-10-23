package com.spaceapp.presentation.explore_detail

sealed interface CategoryState {
    object Overview : CategoryState
    object Information : CategoryState
}
