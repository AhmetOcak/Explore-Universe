package com.spaceapp.presentation.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.common.EpochConverter
import com.spaceapp.core.ui.component.*
import com.spaceapp.domain.model.where_is_the_iss.Iss
import com.spaceapp.domain.model.mars_photos.MarsPhoto
import com.spaceapp.domain.model.people_in_space.PeopleInSpace
import com.spaceapp.presentation.utils.HomeScreenConstants

private val constants = HomeScreenConstants

@Composable
fun HomeScreen(modifier: Modifier = Modifier, viewModel: HomeViewModel) {
    val marsPhotoState by viewModel.marsPhotoState.collectAsState()
    val whereIsTheIssState by viewModel.whereIsTheIssState.collectAsState()
    val peopleInSpaceState by viewModel.peopleInSpaceState.collectAsState()

    val activity = LocalContext.current as Activity
    OnBackPressed(activity = activity)

    HomeScreenContent(
        modifier = modifier,
        marsPhotoState = marsPhotoState,
        whereIsTheIssState = whereIsTheIssState,
        peopleInSpaceState = peopleInSpaceState,
        context = LocalContext.current,
        marsPhotoComingFromDb = viewModel.isMarsPhotoDataTakenFromDatabase,
        peopleComingFromDb = viewModel.isPeopleInSpaceDataTakenFromDatabase
    )
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
private fun HomeScreenContent(
    modifier: Modifier,
    marsPhotoState: MarsPhotoState,
    whereIsTheIssState: WhereIsTheIssState,
    peopleInSpaceState: PeopleInSpaceState,
    context: Context,
    marsPhotoComingFromDb: Boolean,
    peopleComingFromDb: Boolean
) {
    Scaffold(modifier = modifier.fillMaxSize()) {
        BackgroundImage(modifier = modifier.fillMaxSize(), imageId = R.drawable.background_image)
        Column(
            modifier = modifier
                .fillMaxSize()
                .statusBarsPadding()
                .verticalScroll(rememberScrollState())
                .padding(vertical = 16.dp)
                .navigationBarsPadding(),
            horizontalAlignment = Alignment.Start
        ) {
            WordOfCarlSagan(modifier = modifier)
            WhereIsTheIssSection(
                modifier = modifier,
                whereIsTheIssState = whereIsTheIssState
            )
            PeopleInSpaceRightNow(
                modifier = modifier,
                peopleInSpaceState = peopleInSpaceState,
                dataComingFromDb = peopleComingFromDb
            )
            MarsPhotos(
                modifier = modifier,
                marsPhotoState = marsPhotoState,
                context = context,
                dataComingFromDb = marsPhotoComingFromDb
            )
        }
    }
}

@Composable
private fun WordOfCarlSagan(modifier: Modifier) {
    Row(
        modifier = modifier
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = constants.carl_sagan_word,
                style = MaterialTheme.typography.h1
            )
            Text(
                modifier = modifier.padding(top = 16.dp),
                text = constants.carl_sagan,
                style = MaterialTheme.typography.h3
            )
        }
        Gif(
            modifier = modifier
                .clip(RoundedCornerShape(10))
                .fillMaxWidth()
                .weight(1f)
                .height(250.dp),
            gifId = R.drawable.sun
        )
    }
}

@Composable
private fun WhereIsTheIssSection(
    modifier: Modifier,
    whereIsTheIssState: WhereIsTheIssState
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 32.dp, start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = constants.where_is_the_iss_title,
            style = MaterialTheme.typography.h1
        )
        when (whereIsTheIssState) {
            is WhereIsTheIssState.Loading -> {
                LoadingSpinner(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp)
                )
            }
            is WhereIsTheIssState.Success -> {
                Card(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    IssPositionInfo(
                        modifier = modifier,
                        data = whereIsTheIssState.data!!,
                    )
                }
            }
            is WhereIsTheIssState.Error -> {
                ErrorCard(
                    errorDescription = whereIsTheIssState.errorMessage!!,
                    paddingValues = PaddingValues(top = 16.dp)
                )
            }
        }
    }
}

@Composable
private fun IssPositionInfo(modifier: Modifier, data: Iss) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Gif(
            modifier = modifier
                .fillMaxWidth()
                .height(150.dp),
            gifId = R.drawable.iss
        )
        Text(
            modifier = modifier.padding(start = 16.dp, top = 16.dp),
            text = EpochConverter.readTimestamp(data.date),
            style = MaterialTheme.typography.h4
        )
        Text(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 32.dp),
            text = "The ISS is ${data.altitude.toInt()}km above the Earth",
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center,
        )
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = constants.iss_speed,
                    style = MaterialTheme.typography.h4
                )
                Underline(width = 64.dp)
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = "${data.velocity.toInt()}km",
                    style = MaterialTheme.typography.body1
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = constants.iss_visibility,
                    style = MaterialTheme.typography.h4
                )
                Underline(width = 64.dp)
                Text(
                    modifier = modifier.padding(top = 8.dp),
                    text = data.visibility,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}

@Composable
private fun PeopleInSpaceRightNow(
    modifier: Modifier,
    peopleInSpaceState: PeopleInSpaceState,
    dataComingFromDb: Boolean
) {
    PeopleInSpaceTitle(modifier = modifier)
    when (peopleInSpaceState) {
        is PeopleInSpaceState.Loading -> {
            LoadingSpinner(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            )
        }
        is PeopleInSpaceState.Success -> {
            PeopleInSpaceList(
                modifier = modifier,
                data = peopleInSpaceState.data!!,
                dataComingFromDb = dataComingFromDb
            )
        }
        is PeopleInSpaceState.Error -> {
            ErrorCard(errorDescription = peopleInSpaceState.errorMessage!!)
        }
    }
}

@Composable
private fun PeopleInSpaceTitle(modifier: Modifier) {
    Text(
        modifier = modifier.padding(top = 32.dp, start = 16.dp),
        text = constants.people_in_space_right_now_title,
        style = MaterialTheme.typography.h1,
        textAlign = TextAlign.Start
    )
}

@Composable
private fun PeopleInSpaceList(
    modifier: Modifier,
    data: List<PeopleInSpace>,
    dataComingFromDb: Boolean
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = MaterialTheme.colors.surface),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PeopleInSpace(
                modifier = modifier.weight(1f),
                dataComingFromDb = dataComingFromDb,
                data = data
            )
        }
    }
}

@Composable
private fun PeopleInSpace(
    modifier: Modifier,
    dataComingFromDb: Boolean,
    data: List<PeopleInSpace>
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = constants.astronaut_name, style = MaterialTheme.typography.h4)
        Underline(width = 64.dp)
        AstronautName(dataComingFromDb = dataComingFromDb, data = data)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = constants.astronaut_craft, style = MaterialTheme.typography.h4)
        Underline(width = 64.dp)
        AstronautCraft(dataComingFromDb = dataComingFromDb, data = data)
    }
}

@Composable
private fun AstronautName(dataComingFromDb: Boolean, data: List<PeopleInSpace>) {
    if (dataComingFromDb) {
        data.forEach {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = it.people[0].name, style = MaterialTheme.typography.body1)
            }
        }
    } else {
        data[0].people.forEach {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = it.name, style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
private fun AstronautCraft(dataComingFromDb: Boolean, data: List<PeopleInSpace>) {
    if (dataComingFromDb) {
        data.forEach {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = it.people[0].craft, style = MaterialTheme.typography.body1)
            }
        }
    } else {
        data[0].people.forEach {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = it.craft, style = MaterialTheme.typography.body1)
            }
        }
    }
}

@Composable
private fun MarsPhotos(
    modifier: Modifier,
    marsPhotoState: MarsPhotoState,
    context: Context,
    dataComingFromDb: Boolean
) {
    Column(modifier = modifier.fillMaxWidth()) {
        MarsPhotosTitle(modifier = modifier)
        when (marsPhotoState) {
            is MarsPhotoState.Loading -> {
                LoadingSpinner(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 48.dp, bottom = 16.dp)
                )
            }
            is MarsPhotoState.Success -> {
                MarsPhotoList(
                    modifier = modifier,
                    dataComingFromDb = dataComingFromDb,
                    photoList = marsPhotoState.data!!,
                    context = context
                )
            }
            is MarsPhotoState.Error -> {
                ErrorCard(errorDescription = marsPhotoState.errorMessage!!)
            }
        }
    }
}

@Composable
private fun MarsPhotosTitle(modifier: Modifier) {
    Text(
        modifier = modifier.padding(start = 16.dp, end = 16.dp, top = 32.dp, bottom = 16.dp),
        text = constants.photos_from_mars_title,
        style = MaterialTheme.typography.h1
    )
}

@Composable
private fun MarsPhotoList(
    modifier: Modifier,
    dataComingFromDb: Boolean,
    photoList: List<MarsPhoto>,
    context: Context
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 32.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        if (dataComingFromDb) {
            items(photoList) {
                MarsCard(
                    modifier = modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                        .height(300.dp),
                    earthDate = it.photos[0].date,
                    rover = it.photos[0].rover.name,
                    marsImageUrl = it.photos[0].image,
                    context = context
                )
            }
        } else {
            items(photoList[0].photos) {
                MarsCard(
                    modifier = modifier
                        .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                        .height(300.dp),
                    earthDate = it.date,
                    rover = it.rover.name,
                    marsImageUrl = it.image,
                    context = context
                )
            }
        }
    }
}

@Composable
private fun OnBackPressed(activity: Activity) {
    BackHandler {
        activity.finish()
    }
}