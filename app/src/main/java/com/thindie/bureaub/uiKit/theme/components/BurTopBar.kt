package com.thindie.bureaub.uiKit.theme.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp

@Composable
fun BurTopBar(modifier: Modifier = Modifier, title: String? = null, onClose: () -> Unit) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically

    ) {
        IconButton(onClick = onClose) {
            Icon(
                rememberVectorPainter(image = Icons.Default.Clear),
                contentDescription = null
            )
        }
        title?.let {
            Text(
                text = title,
                modifier = Modifier
                    .padding(32.dp),
                style = MaterialTheme.typography.headlineLarge
            )
        }

    }
}