package com.spaceapp.presentation.profile.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.Gif
import com.spaceapp.presentation.profile.ProfileViewModel

@Composable
fun ProfilePreviewSection(modifier: Modifier, viewModel: ProfileViewModel) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = LocalConfiguration.current.screenHeightDp.dp / 4),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        UserProfileImage(
            modifier = modifier
        )
        UserProfileName(
            modifier = modifier,
            viewModel = viewModel
        )
    }
}

@Composable
private fun UserProfileImage(modifier: Modifier) {
    Gif(modifier = modifier.clip(CircleShape), gifId = R.drawable.profile)
}

@Composable
private fun UserProfileName(modifier: Modifier, viewModel: ProfileViewModel) {
    Text(
        modifier = modifier.padding(top = 32.dp, start = 16.dp, end = 16.dp),
        text = if (viewModel.currentUserGms != null) viewModel.currentUserGms.email!! else
            viewModel.currentUserHms?.email!!,
        style = MaterialTheme.typography.h2,
        color = MaterialTheme.colors.secondary,
        textAlign = TextAlign.Center
    )
}