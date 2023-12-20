package com.spaceapp.core.designsystem.component

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import com.spaceapp.core.designsystem.theme.White500
import com.spaceapp.core.designsystem.theme.fonts

@Composable
fun DefaultOutlinedTextField(
    modifier: Modifier = Modifier,
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
            Text(
                text = labelText,
                fontFamily = fonts,
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold
            )
        },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        keyboardActions = KeyboardActions(onNext = {}),
        maxLines = 1,
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation(
            mask = '*'
        ) else VisualTransformation.None,
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
            focusedLeadingIconColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = White500,
            unfocusedLabelColor = White500,
            unfocusedLeadingIconColor = White500
        ),
        singleLine = true
    )
}