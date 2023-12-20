package com.spaceapp.presentation.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spaceapp.core.designsystem.component.ErrorCard
import com.spaceapp.core.designsystem.component.LoadingSpinner
import com.spaceapp.core.designsystem.component.Underline
import com.spaceapp.domain.model.people_in_space.PeopleInSpace
import com.spaceapp.presentation.home.state.PeopleInSpaceState
import com.spaceapp.presentation.utils.HomeScreenConstants

@Composable
fun PeopleInSpaceRightNowSection(
    peopleInSpaceState: PeopleInSpaceState,
    dataComingFromDb: Boolean,
    retryAstronautsInfo: () -> Unit
) {
    PeopleInSpaceTitle(modifier = Modifier.padding(top = 32.dp, start = 16.dp))
    when (peopleInSpaceState) {
        is PeopleInSpaceState.Loading -> {
            LoadingSpinner(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 48.dp)
            )
        }

        is PeopleInSpaceState.Success -> {
            PeopleInSpaceList(
                data = peopleInSpaceState.data!!,
                dataComingFromDb = dataComingFromDb
            )
        }

        is PeopleInSpaceState.Error -> {
            ErrorCard(
                errorDescription = peopleInSpaceState.errorMessage!!,
                paddingValues = PaddingValues(top = 16.dp),
                isButtonAvailable = true,
                buttonText = "Try Again",
                onClick = retryAstronautsInfo
            )
        }
    }
}

@Composable
private fun PeopleInSpaceTitle(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = HomeScreenConstants.people_in_space_right_now_title,
        style = MaterialTheme.typography.headlineLarge,
        textAlign = TextAlign.Start
    )
}

@Composable
private fun PeopleInSpaceList(
    data: List<PeopleInSpace>,
    dataComingFromDb: Boolean
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(color = MaterialTheme.colorScheme.surface),
        horizontalAlignment = Alignment.Start
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp, bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            PeopleInSpace(
                modifier = Modifier.weight(1f),
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
        Text(text = HomeScreenConstants.astronaut_name, style = MaterialTheme.typography.displayMedium)
        Underline(width = 64.dp)
        AstronautName(dataComingFromDb = dataComingFromDb, data = data)
    }
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = HomeScreenConstants.astronaut_craft, style = MaterialTheme.typography.displayMedium)
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
                Text(text = it.people[0].name, style = MaterialTheme.typography.bodyMedium)
            }
        }
    } else {
        data[0].people.forEach {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = it.name, style = MaterialTheme.typography.bodyMedium)
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
                Text(text = it.people[0].craft, style = MaterialTheme.typography.bodyMedium)
            }
        }
    } else {
        data[0].people.forEach {
            Row(
                modifier = Modifier.padding(top = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(text = it.craft, style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}