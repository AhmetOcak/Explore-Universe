package com.spaceapp.core.navigation

object NavName {
    const val explore_detail_screen_name = "explore_detail_screen"
    const val news_detail_screen = "news_detail_screen"
}

object NavRoutes {
    const val profile_screen = "profile_screen"
    const val home_screen = "home_screen"
    const val news_screen = "news_screen"
    const val explore_screen = "explore_screen"
    const val universe_glossary = "universe_glossary"
    const val login_screen = "login_screen"
    const val news_detail_screen = "${NavName.news_detail_screen}/{newsUrl}"
    const val explore_detail_screen = "${NavName.explore_detail_screen_name}/{name}/{description}/{info1}/{info2}"
    const val sign_up_screen = "sign_up_screen"
    const val forgot_password_screen = "forgot_password"
}

object BottomNavItemTexts {
    const val profile = "Profile"
    const val home = "Home"
    const val news = "News"
    const val explore = "Explore"
    const val terms = "Glossary"
}

object BottomNavItems {
    val items = listOf(
        BottomNavItem.Explore,
        BottomNavItem.News,
        BottomNavItem.Home,
        BottomNavItem.Terms,
        BottomNavItem.Profile
    )
}