package com.spaceapp.core.navigation

sealed class NavScreen(val route: String) {
    object ProfileScreen : NavScreen(route = NavRoutes.profile_screen)
    object HomeScreen : NavScreen(route = NavRoutes.home_screen)
    object NewsScreen : NavScreen(route = NavRoutes.news_screen)
    object ExploreScreen : NavScreen(route = NavRoutes.explore_screen)
    object UniverseGlossaryScreen : NavScreen(route = NavRoutes.universe_glossary)
    object LoginScreen : NavScreen(route = NavRoutes.login_screen)
    object NewsDetailScreen : NavScreen(route = NavRoutes.news_detail_screen)
    object ExploreDetailScreen: NavScreen(route = NavRoutes.explore_detail_screen)
    object SignUpScreen: NavScreen(route = NavRoutes.sign_up_screen)
    object ForgotPasswordScreen: NavScreen(route = NavRoutes.forgot_password_screen)
}
