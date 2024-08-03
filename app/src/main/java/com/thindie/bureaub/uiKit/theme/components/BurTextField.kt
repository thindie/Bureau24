package com.thindie.bureaub.uiKit.theme.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

@Composable
fun BurTextField(
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
) {
    MaterialTheme(colorScheme = lightColorScheme(), content = {
        OutlinedTextField(
            placeholder = { Text(text = placeholder) },
            value = value,
            onValueChange = onValueChange
        )
    })
}