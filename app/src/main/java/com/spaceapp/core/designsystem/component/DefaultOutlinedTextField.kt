package com.spaceapp.core.designsystem.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultOutlinedTextField(
    modifier: Modifier,
    value: String = "",
    onValueChanged: (String) -> Unit,
    labelText: String,
    keyboardType: KeyboardType,
    leadingIconId: Int,
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChanged,
        label = {
            Text(text = labelText, style = MaterialTheme.typography.bodySmall)
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = KeyboardActions(onNext = {}),
        maxLines = 1,
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation(mask = '*') else VisualTransformation.None,
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIconId),
                contentDescription = null
            )
        },
        textStyle = MaterialTheme.typography.bodyMedium,
        colors = OutlinedTextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.secondary,
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            focusedLabelColor = MaterialTheme.colorScheme.secondary,
        ),
        singleLine = true
    )
}