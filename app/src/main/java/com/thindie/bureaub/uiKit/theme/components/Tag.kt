package com.thindie.bureaub.uiKit.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview

private const val CAGE = "#"

@Composable
fun BurTag(modifier: Modifier = Modifier, onClick: () -> Unit, label: String) {
    ElevatedAssistChip(
        shape = RectangleShape,
        modifier = Modifier,
        onClick = onClick,
        label = { Text(text = CAGE.plus(label)) })
}

@Preview
@Composable
private fun TagPreview() {
    MaterialTheme {
        Row(
            modifier = Modifier
                .background(color = Color.White)
                .fillMaxWidth()
        ) {
            BurTag(onClick = {}, label = "tag")
        }

    }
}