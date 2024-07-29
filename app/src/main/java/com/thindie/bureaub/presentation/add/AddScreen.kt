package com.thindie.bureaub.presentation.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.routing.Destination

fun NavGraphBuilder.add() = Screen<AddViewModel>(
    destination = Destination.Add,
    screen = { viewModel ->

        Box(modifier = Modifier.fillMaxSize()) {
            Text(modifier = Modifier.align(Alignment.Center), text = "add")

            Button(
                onClick = viewModel::current,
                modifier = Modifier.align(Alignment.BottomCenter)
            ) {
                Text(text = "current")
            }
        }
    })