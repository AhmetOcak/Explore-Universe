package com.spaceapp.presentation.universe_glossary.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
    val interactionSource = remember { MutableInteractionSource() }

    GlossaryCard(
        iconOnClick = {
            isOpen = !isOpen
            isExpanded = !isExpanded
        },
        isOpen = isOpen,
        isExpanded = isExpanded,
        title = title,
        description = description,
        imageId = imageId,
        cardOnClick = {
            isOpen = !isOpen
            isExpanded = !isExpanded
        },
        interactionSource = interactionSource
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
    imageId: Int,
    cardOnClick: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = cardOnClick
            )
    ) {
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
                    style = MaterialTheme.typography.displayMedium
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
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}