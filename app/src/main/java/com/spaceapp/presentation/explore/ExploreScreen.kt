package com.spaceapp.presentation.explore

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.*
import com.spaceapp.presentation.explore.components.*
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
        viewModel = viewModel,
        navController = navController
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun ExploreScreenContent(
    modifier: Modifier,
    apodState: ApodState,
    exploreGalaxyState: ExploreGalaxyState,
    exploreCategoryState: ExploreCategoryState,
    viewModel: ExploreViewModel,
    navController: NavController
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
        BackgroundImage(modifier = modifier.fillMaxSize(), imageId = R.drawable.background_image)
        Column(
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(top = 16.dp)
        ) {
            PageTitle(modifier = modifier)
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top,
            ) {
                ExploreSection(
                    modifier = modifier,
                    exploreGalaxyState = exploreGalaxyState,
                    exploreCategoryState = exploreCategoryState,
                    viewModel = viewModel,
                    navController = navController
                )
                UniverseImageSection(
                    modifier = modifier,
                    apodState = apodState
                )
            }
        }
    }
}

@Composable
private fun ExploreSection(
    modifier: Modifier,
    exploreGalaxyState: ExploreGalaxyState,
    exploreCategoryState: ExploreCategoryState,
    viewModel: ExploreViewModel,
    navController: NavController
) {
    CategorySection(
        modifier = modifier,
        viewModel = viewModel
    )
    ExploreCardSection(
        modifier = modifier,
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