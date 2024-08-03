package com.thindie.bureaub.uiKit.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

private const val NUM = "№"

@Composable
fun BurCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    id: String,
    tags: List<String>,
    onClickTag: (String) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = NUM.plus(id),
                style = MaterialTheme.typography.headlineSmall,
                color = LocalContentColor.current.copy(0.2f)
            )
            repeat(3) {
                Spacer(
                    Modifier
                        .padding(horizontal = 4.dp)
                        .size(8.dp)
                        .background(Color.Black, CircleShape)

                )
            }
        }

        Row() {
            Text(text = title, style = MaterialTheme.typography.displaySmall)
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row() {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = LocalContentColor.current.copy(0.3f)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tags) {
                BurTag(onClick = { onClickTag(it) }, label = it)
            }
        }
    }
}

@Preview
@Composable
private fun BurCardPreview() {
    MaterialTheme {
        Column(Modifier.background(Color.White)) {
            BurCard(modifier = Modifier.wrapContentHeight(),
                title = "vocent",
                id = "1",
                description = "eqweeqweqweqweqweqeqeqweeqwewqeeqweqweqeqweqweqweqwe",
                tags = listOf("asd", "bcd", "dad"),
                onClickTag = { })
        }
    }
}