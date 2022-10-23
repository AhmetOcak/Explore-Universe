package com.spaceapp.core.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.spaceapp.R

@Composable
fun VerifyCodeCard(
    modifier: Modifier,
    userEmail: String,
    value: String = "",
    onValueChanged: (String) -> Unit,
    onClick: () -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(420.dp)
                .padding(horizontal = 32.dp),
            backgroundColor = MaterialTheme.colors.surface,
            shape = RoundedCornerShape(15)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    modifier = Modifier.padding(vertical = 16.dp).size(120.dp),
                    painter = painterResource(id = R.drawable.verify),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Please type the verification code sent to $userEmail",
                    style = MaterialTheme.typography.h3,
                    textAlign = TextAlign.Center
                )
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    value = value,
                    onValueChange = onValueChanged,
                    label = {
                        Text(text = "verify code", style = MaterialTheme.typography.body2)
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    maxLines = 1,
                    leadingIcon = {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_check_box),
                            contentDescription = null
                        )
                    },
                    textStyle = MaterialTheme.typography.body1,
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = MaterialTheme.colors.secondary,
                        focusedBorderColor = MaterialTheme.colors.secondary,
                        focusedLabelColor = MaterialTheme.colors.secondary,
                    )
                )
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
                    onClick = onClick,
                    content = { Text(text = "Verify") },
                    shape = RoundedCornerShape(50),
                    enabled = true,
                )
            }
        }
    }
}