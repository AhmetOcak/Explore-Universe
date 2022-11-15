package com.spaceapp.presentation.explore

import android.annotation.SuppressLint
import android.app.Activity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.ui.component.*
import com.spaceapp.core.ui.theme.FullTransparentKimberly
import com.spaceapp.core.ui.theme.TransparentKimberly
import com.spaceapp.domain.model.explore_galaxy_data.Object
import com.spaceapp.presentation.utils.ExploreCategories
import com.spaceapp.presentation.utils.ExploreScreenConstants
import com.spaceapp.presentation.utils.NoData
import com.spaceapp.presentation.utils.SpaceObjectImageType

private val constants = ExploreScreenConstants

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
private fun PageTitle(modifier: Modifier) {
    Text(
        modifier = modifier.padding(start = 16.dp),
        text = constants.title_1,
        style = MaterialTheme.typography.h1
    )
    Divider(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        thickness = 1.dp
    )
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
private fun CategorySection(modifier: Modifier = Modifier, viewModel: ExploreViewModel) {
    var selected by rememberSaveable { mutableStateOf(0) }

    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        itemsIndexed(categories) { index, item ->
            DefaultTextButton(
                category = item,
                index = index,
                onClick = {
                    selected = index
                    viewModel.exploreCategoryOnClick(categoryName = item)
                },
                selected = selected
            )
        }
    }
}

@Composable
private fun ExploreCardSection(
    modifier: Modifier,
    exploreGalaxyState: ExploreGalaxyState,
    exploreCategoryState: ExploreCategoryState,
    navController: NavController
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        when (exploreGalaxyState) {
            is ExploreGalaxyState.Success -> {
                when (exploreCategoryState) {
                    is ExploreCategoryState.ALL -> {
                        showAllExploreData(
                            spaceObjects = exploreGalaxyState.data!!.spaceObjects,
                            navController = navController
                        )
                    }
                    is ExploreCategoryState.PLANETS -> {
                        items(exploreGalaxyState.data!!.spaceObjects[0].planets) {
                            ExploreCard(
                                name = it.planetName,
                                imageId = SpaceObjectImageType.setSpaceObjectImageType(it.planetName),
                                imagePadding = PaddingValues(top = 8.dp),
                                backgroundColor = FullTransparentKimberly,
                                navController = navController,
                                description = it.planetDescription,
                                info1 = it.planetRadius.toString(),
                                info2 = it.distanceFromSun.toString()
                            )
                        }
                    }
                    is ExploreCategoryState.MOONS -> {
                        items(exploreGalaxyState.data!!.spaceObjects[0].moons) {
                            ExploreCard(
                                name = it.moonName,
                                imageId = SpaceObjectImageType.setSpaceObjectImageType(it.moonName),
                                imagePadding = PaddingValues(top = 8.dp),
                                backgroundColor = FullTransparentKimberly,
                                navController = navController,
                                description = it.moonDescription,
                                info1 = it.moonRadius.toString(),
                                info2 = it.distanceFromSun.toString()
                            )
                        }
                    }
                    is ExploreCategoryState.METEORS -> {
                        items(exploreGalaxyState.data!!.spaceObjects[0].meteors) {
                            ExploreCard(
                                name = it.meteorName,
                                imageId = SpaceObjectImageType.setSpaceObjectImageType(it.meteorName),
                                backgroundColor = TransparentKimberly,
                                contentScale = ContentScale.Crop,
                                navController = navController,
                                description = it.meteorDescription,
                                info1 = NoData.noData,
                                info2 = it.meteorVelocity.toString()
                            )
                        }
                    }
                    is ExploreCategoryState.COMETS -> {
                        items(exploreGalaxyState.data!!.spaceObjects[0].comets) {
                            ExploreCard(
                                name = it.cometName,
                                imageId = SpaceObjectImageType.setSpaceObjectImageType(it.cometName),
                                backgroundColor = TransparentKimberly,
                                contentScale = ContentScale.Crop,
                                navController = navController,
                                description = it.cometDescription,
                                info1 = it.cometRadius.toString(),
                                info2 = NoData.noData
                            )
                        }
                    }
                    is ExploreCategoryState.STARS -> {
                        items(exploreGalaxyState.data!!.spaceObjects[0].stars) {
                            ExploreCard(
                                name = it.starName,
                                imageId = SpaceObjectImageType.setSpaceObjectImageType(it.starName),
                                backgroundColor = TransparentKimberly,
                                contentScale = ContentScale.Crop,
                                navController = navController,
                                description = it.starDescription,
                                info1 = NoData.noData,
                                info2 = NoData.noData
                            )
                        }
                    }
                }
            }
            else -> {}
        }
    }
}

@Composable
private fun UniverseImageSection(modifier: Modifier, apodState: ApodState) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            modifier = modifier.padding(start = 16.dp, top = 32.dp, bottom = 16.dp),
            text = constants.title_2,
            style = MaterialTheme.typography.h2
        )
        when (apodState) {
            is ApodState.Loading -> {
                LoadingSpinner(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp)
                )
            }
            is ApodState.Success -> {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(apodState.apodData!!) {
                        ApodCard(
                            title = it.title,
                            context = LocalContext.current,
                            imageUrl = it.image,
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
            is ApodState.Error -> {
                ErrorCard(errorDescription = apodState.errorMessage.toString())
            }
        }
    }
}

private fun LazyListScope.showAllExploreData(
    spaceObjects: List<Object>,
    navController: NavController
) {
    items(spaceObjects[0].planets) {
        ExploreCard(
            name = it.planetName,
            imageId = SpaceObjectImageType.setSpaceObjectImageType(it.planetName),
            imagePadding = PaddingValues(top = 8.dp),
            backgroundColor = FullTransparentKimberly,
            navController = navController,
            description = it.planetDescription,
            info1 = it.planetRadius.toString(),
            info2 = it.distanceFromSun.toString()
        )
    }

    items(spaceObjects[0].moons) {
        ExploreCard(
            name = it.moonName,
            imageId = SpaceObjectImageType.setSpaceObjectImageType(it.moonName),
            imagePadding = PaddingValues(top = 8.dp),
            backgroundColor = FullTransparentKimberly,
            navController = navController,
            description = it.moonDescription,
            info1 = it.moonRadius.toString(),
            info2 = it.distanceFromSun.toString()
        )
    }

    items(spaceObjects[0].meteors) {
        ExploreCard(
            name = it.meteorName,
            imageId = SpaceObjectImageType.setSpaceObjectImageType(it.meteorName),
            backgroundColor = TransparentKimberly,
            contentScale = ContentScale.Crop,
            navController = navController,
            description = it.meteorDescription,
            info1 = NoData.noData,
            info2 = it.meteorVelocity.toString()
        )
    }

    items(spaceObjects[0].comets) {
        ExploreCard(
            name = it.cometName,
            imageId = SpaceObjectImageType.setSpaceObjectImageType(it.cometName),
            backgroundColor = TransparentKimberly,
            contentScale = ContentScale.Crop,
            navController = navController,
            description = it.cometDescription,
            info1 = it.cometRadius.toString(),
            info2 = NoData.noData
        )
    }

    items(spaceObjects[0].stars) {
        ExploreCard(
            name = it.starName,
            imageId = SpaceObjectImageType.setSpaceObjectImageType(it.starName),
            backgroundColor = TransparentKimberly,
            contentScale = ContentScale.Crop,
            navController = navController,
            description = it.starDescription,
            info1 = NoData.noData,
            info2 = NoData.noData
        )
    }
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}

private val exploreCategoriesConstants = ExploreCategories

private val categories = listOf(
    exploreCategoriesConstants.all,
    exploreCategoriesConstants.planets,
    exploreCategoriesConstants.moons,
    exploreCategoriesConstants.meteors,
    exploreCategoriesConstants.comets,
    exploreCategoriesConstants.stars,
)