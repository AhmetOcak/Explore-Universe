package com.spaceapp.core.designsystem.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.spaceapp.R
import com.spaceapp.core.designsystem.theme.ErrorColor
import com.spaceapp.core.designsystem.theme.TransparentKimberly
import com.spaceapp.core.designsystem.theme.White

@Composable
fun ErrorCard(
    modifier: Modifier = Modifier,
    errorDescription: String,
    paddingValues: PaddingValues = PaddingValues(16.dp),
    errorIconSize: Dp = 96.dp,
    height: Dp = 250.dp,
    onClick: () -> Unit = {},
    isButtonAvailable: Boolean = false,
    buttonText: String = "OK"
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(if (isButtonAvailable) 300.dp else height)
            .padding(paddingValues),
        shape = RoundedCornerShape(15),
        colors = CardDefaults.cardColors(containerColor = TransparentKimberly),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(errorIconSize),
                painter = painterResource(id = R.drawable.error),
                contentDescription = null,
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(top = 16.dp),
                text = "OOPS"
            )
            Text(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
                text = errorDescription,
                textAlign = TextAlign.Center
            )
            if(isButtonAvailable) {
                OutlinedButton(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp, start = 32.dp, end = 32.dp),
                    onClick = onClick,
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = ErrorColor)
                ) {
                    Text(text = buttonText, style = MaterialTheme.typography.labelMedium, color = White)
                }
            }
        }
    }
}