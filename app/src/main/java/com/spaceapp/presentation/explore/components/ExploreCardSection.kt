package com.spaceapp.presentation.explore.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spaceapp.core.designsystem.theme.FullTransparentKimberly
import com.spaceapp.core.designsystem.theme.TransparentKimberly
import com.spaceapp.domain.model.explore_galaxy_data.Object
import com.spaceapp.presentation.explore.state.ExploreCategoryState
import com.spaceapp.presentation.explore.state.ExploreGalaxyState
import com.spaceapp.presentation.utils.NoData
import com.spaceapp.presentation.utils.SpaceObjectImageType

@Composable
fun ExploreCardSection(
    exploreGalaxyState: ExploreGalaxyState,
    exploreCategoryState: ExploreCategoryState,
    navController: NavController
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
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