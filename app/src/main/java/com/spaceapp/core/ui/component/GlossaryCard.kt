package com.spaceapp.core.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.spaceapp.R

@Composable
fun StatefulGlossaryCard(
    title: String,
    description: String,
    imageId: Int
) {
    var isOpen by rememberSaveable { mutableStateOf(false) }
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    GlossaryCard(
        iconOnClick = {
            isOpen = !isOpen
            isExpanded = !isExpanded
        },
        isOpen = isOpen,
        isExpanded = isExpanded,
        title = title,
        description = description,
        imageId = imageId
    )
}

@Composable
private fun GlossaryCard(
    modifier: Modifier = Modifier,
    iconOnClick: () -> Unit,
    isOpen: Boolean,
    isExpanded: Boolean,
    title: String,
    description: String,
    imageId: Int
) {
    Card(modifier = modifier.fillMaxWidth(), elevation = 4.dp) {
        Column(horizontalAlignment = Alignment.Start) {
            Image(
                modifier = modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = imageId),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = modifier.padding(start = 16.dp),
                    text = title,
                    style = MaterialTheme.typography.h4
                )
                IconButton(onClick = iconOnClick) {
                    Icon(
                        modifier = modifier.scale(1f, if (isOpen) -1f else 1f),
                        painter = painterResource(id = R.drawable.ic_baseline_expand_more),
                        contentDescription = null
                    )
                }
            }
            AnimatedVisibility(visible = isExpanded) {
                Text(
                    modifier = modifier.padding(
                        start = 16.dp,
                        end = 16.dp,
                        bottom = 16.dp
                    ),
                    text = description,
                    style = MaterialTheme.typography.body1
                )
            }
        }
    }
}