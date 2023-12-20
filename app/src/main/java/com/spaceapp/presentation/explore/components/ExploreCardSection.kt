package com.spaceapp.presentation.explore.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.spaceapp.R
import com.spaceapp.core.designsystem.theme.FullTransparentKimberly
import com.spaceapp.core.designsystem.theme.TransparentKimberly
import com.spaceapp.core.designsystem.theme.White
import com.spaceapp.core.navigation.NavName
import com.spaceapp.core.navigation.NavScreen
import com.spaceapp.domain.model.explore_galaxy_data.Object
import com.spaceapp.presentation.explore.state.ExploreCategoryState
import com.spaceapp.presentation.explore.state.ExploreGalaxyState
import com.spaceapp.presentation.utils.NoData
import com.spaceapp.presentation.utils.SpaceObjectImageType

@Composable
fun ExploreCardSection(
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
private fun ExploreCard(
    modifier: Modifier = Modifier,
    navController: NavController,
    name: String,
    description: String,
    info1: String,
    info2: String,
    imageId: Int,
    imagePadding: PaddingValues = PaddingValues(0.dp),
    contentScale: ContentScale = ContentScale.Fit,
    backgroundColor: Color = MaterialTheme.colorScheme.surface
) {
    Box {
        Card(
            modifier = modifier
                .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                .height(320.dp)
                .clickable {
                    try {
                        navController.navigate("${NavName.explore_detail_screen_name}/$name/$description/$info1/$info2") {
                            popUpTo(route = NavScreen.ExploreScreen.route)
                        }
                    } catch (e: Exception) {
                        Log.e("e", e.toString())
                    }
                },
            shape = RoundedCornerShape(32.dp),
            colors = CardDefaults.cardColors(backgroundColor),
        ) {
            Column(
                modifier = modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                Image(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(220.dp)
                        .weight(6f)
                        .padding(imagePadding),
                    painter = painterResource(id = imageId),
                    contentDescription = null,
                    contentScale = contentScale
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .weight(2f),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = modifier.padding(start = 16.dp, bottom = 16.dp),
                        text = name,
                        style = MaterialTheme.typography.headlineSmall
                    )
                    IconButton(
                        modifier = modifier.padding(bottom = 16.dp),
                        onClick = {
                            try {
                                navController.navigate("${NavName.explore_detail_screen_name}/$name/$description/$info1/$info2") {
                                    popUpTo(route = NavScreen.ExploreScreen.route)
                                }
                            }catch (e: Exception) {
                                Log.e("e", e.toString())
                            }
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_ios),
                            contentDescription = null,
                            tint = White
                        )
                    }
                }
            }
        }
    }
}