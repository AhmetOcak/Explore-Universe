package com.spaceapp.core.ui.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation

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
            Text(text = labelText, style = MaterialTheme.typography.body2)
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
        textStyle = MaterialTheme.typography.body1,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = MaterialTheme.colors.secondary,
            focusedBorderColor = MaterialTheme.colors.secondary,
            focusedLabelColor = MaterialTheme.colors.secondary,
        ),
        singleLine = true
    )
}