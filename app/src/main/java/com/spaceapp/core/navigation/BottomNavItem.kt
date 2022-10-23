package com.spaceapp.core.navigation

import androidx.annotation.DrawableRes
import com.spaceapp.R

sealed class BottomNavItem(val route: String, val labelText: String, @DrawableRes val icon: Int) {
    object Profile : BottomNavItem(
        NavRoutes.profile_screen,
        BottomNavItemTexts.profile,
        R.drawable.ic_baseline_account_box
    )

    object Home : BottomNavItem(
        NavRoutes.home_screen,
        BottomNavItemTexts.home,
        R.drawable.ic_baseline_home
    )

    object News : BottomNavItem(
        NavRoutes.news_screen,
        BottomNavItemTexts.news,
        R.drawable.ic_baseline_newspaper
    )

    object Explore : BottomNavItem(
        NavRoutes.explore_screen,
        BottomNavItemTexts.explore,
        R.drawable.ic_baseline_explore
    )

    object Terms : BottomNavItem(
        NavRoutes.universe_glossary,
        BottomNavItemTexts.terms,
        R.drawable.ic_baseline_book
    )
}