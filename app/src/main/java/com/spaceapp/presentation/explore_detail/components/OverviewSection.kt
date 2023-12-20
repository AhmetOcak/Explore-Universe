package com.spaceapp.presentation.explore_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.spaceapp.presentation.utils.NoData
import com.spaceapp.presentation.utils.SpaceObjectImageType
import com.spaceapp.presentation.utils.SpaceObjects

@Composable
fun OverViewSection(
    objectName: String?,
    objectInfo1: String?,
    objectInfo2: String?
) {
    objectName?.let { SpaceObjectImageType.setSpaceObjectImageType(it) }?.let {
        OverView(
            title1 = "radius",
            info1 = if (objectInfo1 == NoData.noData) NoData.noData else "r = ${objectInfo1}km",
            title2 = if (isMeteor(objectName)) "velocity" else "distance from sun",
            info2 = if (isMeteor(objectName)) "${objectInfo2}km/s" else "${objectInfo2}km",
            objectName = objectName,
            objectImage = it
        )
    }
}

@Composable
private fun OverView(
    title1: String,
    info1: String,
    title2: String,
    info2: String,
    objectName: String,
    objectImage: Int,
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(15)),
        painter = painterResource(id = objectImage),
        contentDescription = null,
        contentScale = contentScale
    )
    Text(
        modifier = Modifier.padding(bottom = 32.dp),
        text = objectName,
        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 56.sp),
        textAlign = TextAlign.Center
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title1,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = info1,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = title2, style = MaterialTheme.typography.bodySmall)
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = info2,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

private fun isMeteor(name: String): Boolean {
    return when (name) {
        SpaceObjects.lenoids -> { true }
        SpaceObjects.lyrids -> { true }
        SpaceObjects.orinoids -> { true }
        SpaceObjects.perseids -> { true }
        else -> { false }
    }
}