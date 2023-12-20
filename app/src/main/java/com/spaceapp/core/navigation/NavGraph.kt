package com.spaceapp.core.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.Scaffold
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.spaceapp.R
import com.spaceapp.core.designsystem.components.BackgroundImage
import com.spaceapp.core.designsystem.theme.Mirage
import com.spaceapp.core.designsystem.theme.White500
import com.spaceapp.presentation.explore.ExploreScreen
import com.spaceapp.presentation.explore_detail.ExploreDetailScreen
import com.spaceapp.presentation.forgot_password.ForgotPasswordScreen
import com.spaceapp.presentation.home.HomeScreen
import com.spaceapp.presentation.home.HomeViewModel
import com.spaceapp.presentation.login.LoginScreen
import com.spaceapp.presentation.profile.ProfileScreen
import com.spaceapp.presentation.signup.SignUpScreen
import com.spaceapp.presentation.space_news.NewsScreen
import com.spaceapp.presentation.space_news_detail.NewsDetailScreen
import com.spaceapp.presentation.universe_glossary.UniverseGlossaryScreen

@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    startDestination: String,
    homeViewModel: HomeViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    var showFab by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        bottomBar = {
            BottomNavItems.items.forEach { navItem ->
                if (navItem.route == currentRoute) {
                    showFab = true

                    MyBottomNavigationBar(
                        currentRoute = currentRoute,
                        navController = navController
                    )
                }
            }
        },
        floatingActionButton = {
            if (showFab) {
                MyFab(
                    modifier = modifier,
                    currentRoute = currentRoute,
                    navController = navController
                )
            }
        },
        content = {
            BackgroundImage(imageId = R.drawable.background_image)
            NavHost(
                modifier = modifier.padding(it),
                navController = navController,
                startDestination = startDestination
            ) {
                composable(route = NavScreen.ProfileScreen.route) {
                    ProfileScreen(navController = navController)
                    showFab = true
                }
                composable(NavScreen.LoginScreen.route) {
                    LoginScreen(navController = navController)
                    showFab = false
                }
                composable(NavScreen.ForgotPasswordScreen.route) {
                    ForgotPasswordScreen(navController = navController)
                    showFab = false
                }
                composable(route = NavScreen.NewsScreen.route) {
                    NewsScreen(navController = navController)
                    showFab = true
                }
                composable(
                    route = NavScreen.NewsDetailScreen.route,
                    arguments = listOf(navArgument("newsUrl") { type = NavType.StringType }),
                ) {
                    NewsDetailScreen()
                    showFab = false
                }
                composable(NavScreen.UniverseGlossaryScreen.route) {
                    UniverseGlossaryScreen()
                }
                composable(NavScreen.ExploreScreen.route) {
                    ExploreScreen(navController = navController)
                    showFab = true
                }
                composable(NavScreen.HomeScreen.route) {
                    HomeScreen(viewModel = homeViewModel)
                }
                composable(
                    route = NavScreen.ExploreDetailScreen.route,
                    arguments = listOf(
                        navArgument("name") { type = NavType.StringType },
                        navArgument("description") { type = NavType.StringType },
                        navArgument("info1") { type = NavType.StringType },
                        navArgument("info2") { type = NavType.StringType },
                    )
                ) {
                    ExploreDetailScreen()
                    showFab = false
                }
                composable(NavScreen.SignUpScreen.route) {
                    SignUpScreen(navController = navController)
                    showFab = false
                }
            }
        }
    )
}


@Composable
private fun MyBottomNavigationBar(currentRoute: String?, navController: NavController) {
    BottomAppBar(
        modifier = Modifier.navigationBarsPadding(),
        elevation = 8.dp,
        cutoutShape = CircleShape,
        backgroundColor = Mirage
    ) {
        BottomNavItems.items.forEachIndexed { index, screen ->
            if (index != 2) {
                BottomNavigationItem(
                    selected = currentRoute == screen.route,
                    onClick = {
                        if (currentRoute == screen.route) {
                            return@BottomNavigationItem
                        }

                        navController.navigate(screen.route) {
                            NavScreen.HomeScreen.route.let {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(id = screen.icon),
                            contentDescription = null,
                            tint = if (currentRoute == screen.route) {
                                Color.White
                            } else {
                                White500
                            }
                        )
                    },
                    label = {
                        Text(
                            text = screen.labelText,
                            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 12.sp),
                            color = if (currentRoute == screen.route) {
                                Color.White
                            } else {
                                White500
                            }
                        )
                    }
                )
            } else {
                BottomNavigationItem(
                    selected = false,
                    onClick = {},
                    enabled = false,
                    label = {},
                    icon = {}
                )
            }
        }
    }
}

@Composable
private fun MyFab(modifier: Modifier, currentRoute: String?, navController: NavController) {
    FloatingActionButton(
        onClick = {
            if (currentRoute == NavScreen.HomeScreen.route) {
                return@FloatingActionButton
            } else {
                navController.navigate(NavScreen.HomeScreen.route) {
                    NavScreen.HomeScreen.route.let {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        },
        shape = CircleShape,
        containerColor = Color.Transparent
    ) {
        Image(
            modifier = modifier.size(56.dp),
            painter = painterResource(id = R.drawable.earth),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
    }
}