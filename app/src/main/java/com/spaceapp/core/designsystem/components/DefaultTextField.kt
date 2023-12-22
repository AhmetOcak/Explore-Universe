package com.spaceapp.core.designsystem.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.spaceapp.core.designsystem.theme.Mirage
import com.spaceapp.core.designsystem.theme.White500

@Composable
fun DefaultTextField(value: String, onValueChange: (String) -> Unit, placeHolderText: String) {
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeHolderText,
                style = MaterialTheme.typography.displayMedium.copy(color = White500)
            )
        },
        singleLine = true,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Mirage,
            focusedContainerColor = Mirage,
            focusedIndicatorColor = Color.White,
            cursorColor = Color.White
        ),
        textStyle = MaterialTheme.typography.displayMedium
    )
}