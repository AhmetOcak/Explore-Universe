package com.spaceapp.presentation.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.designsystem.component.Gif
import com.spaceapp.presentation.utils.HomeScreenConstants

@Composable
fun WordOfCarlSagan() {
    Row(
        modifier = Modifier
            .width(LocalConfiguration.current.screenWidthDp.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = HomeScreenConstants.carl_sagan_word,
                style = MaterialTheme.typography.headlineLarge
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = HomeScreenConstants.carl_sagan,
                style = MaterialTheme.typography.headlineSmall
            )
        }
        Gif(
            modifier = Modifier
                .clip(RoundedCornerShape(10))
                .fillMaxWidth()
                .weight(1f)
                .height(250.dp),
            gifId = R.drawable.sun
        )
    }
}