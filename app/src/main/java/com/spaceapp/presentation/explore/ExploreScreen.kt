package com.spaceapp.presentation.explore

import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.presentation.explore.components.CategorySection
import com.spaceapp.presentation.explore.components.ExploreCardSection
import com.spaceapp.presentation.explore.components.PageTitle
import com.spaceapp.presentation.explore.components.UniverseImageSection
import com.spaceapp.presentation.explore.state.*

@Composable
fun ExploreScreen(
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
    navController: NavController
) {
    val apodState by viewModel.apodState.collectAsState()
    val exploreGalaxyState by viewModel.exploreGalaxyState.collectAsState()
    val exploreGalaxyCategoryState by viewModel.exploreCategoryState.collectAsState()

    val activity = LocalContext.current as Activity
    OnBackPressed(activity = activity)

    ExploreScreenContent(
        modifier = modifier,
        apodState = apodState,
        exploreGalaxyState = exploreGalaxyState,
        exploreCategoryState = exploreGalaxyCategoryState,
        navController = navController,
        onCategoryClick = {
            viewModel.exploreCategoryOnClick(it)
        }
    )
}

@Composable
private fun ExploreScreenContent(
    modifier: Modifier,
    apodState: ApodState,
    exploreGalaxyState: ExploreGalaxyState,
    exploreCategoryState: ExploreCategoryState,
    navController: NavController,
    onCategoryClick: (String) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(top = 16.dp)
    ) {
        PageTitle()
        Column(
            modifier = modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top,
        ) {
            ExploreSection(
                exploreGalaxyState = exploreGalaxyState,
                exploreCategoryState = exploreCategoryState,
                navController = navController,
                onCategoryClick = onCategoryClick
            )
            UniverseImageSection(apodState = apodState)
        }
    }
}

@Composable
private fun ExploreSection(
    exploreGalaxyState: ExploreGalaxyState,
    exploreCategoryState: ExploreCategoryState,
    navController: NavController,
    onCategoryClick: (String) -> Unit
) {
    CategorySection(onCategoryClick = onCategoryClick)
    ExploreCardSection(
        exploreGalaxyState = exploreGalaxyState,
        exploreCategoryState = exploreCategoryState,
        navController = navController
    )
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}