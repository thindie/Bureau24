package com.thindie.bureaub.uiKit.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.thindie.bureaub.R

@Composable
fun BurButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String = stringResource(R.string.button_title_ok),
) {
    ElevatedButton(
        enabled = enabled,
        onClick = onClick,
        shape = RectangleShape,
        modifier = modifier.height(56.dp),
        elevation = BurButtonDefaults.elevation
    ) {
        Text(text = title, style = MaterialTheme.typography.titleMedium)
    }
}

object BurButtonDefaults {


    val elevation
        @Composable get() = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 8.dp,
            pressedElevation = 5.dp,
            focusedElevation = 4.dp,
            hoveredElevation = 6.dp,
            disabledElevation = 2.dp
        )
}


@Preview
@Composable
private fun BurButtonPreiview() {
    MaterialTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            BurButton(onClick = { })
            BurButton(onClick = { }, modifier = Modifier.fillMaxWidth())
        }
    }
}