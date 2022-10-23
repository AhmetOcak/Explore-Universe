package com.spaceapp.core.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
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
import com.spaceapp.core.navigation.NavName
import com.spaceapp.core.navigation.NavScreen

@Composable
fun ExploreCard(
    modifier: Modifier = Modifier,
    navController: NavController,
    name: String,
    description: String,
    info1: String,
    info2: String,
    imageId: Int,
    imagePadding: PaddingValues = PaddingValues(0.dp),
    contentScale: ContentScale = ContentScale.Fit,
    backgroundColor: Color = MaterialTheme.colors.surface
) {
    Box {
        Card(
            modifier = modifier
                .width(LocalConfiguration.current.screenWidthDp.dp - 96.dp)
                .height(320.dp),
            shape = RoundedCornerShape(32.dp),
            elevation = 4.dp,
            backgroundColor = backgroundColor,
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
                        style = MaterialTheme.typography.h3
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
                            contentDescription = null
                        )
                    }
                }
            }
        }
    }
}