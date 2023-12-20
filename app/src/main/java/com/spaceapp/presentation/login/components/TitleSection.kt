package com.spaceapp.presentation.login.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.spaceapp.presentation.utils.LoginScreenConstants

@Composable
fun TitleSection(modifier: Modifier) {
    Text(
        modifier = modifier,
        text = LoginScreenConstants.welcome_title,
        style = MaterialTheme.typography.headlineLarge
    )
}