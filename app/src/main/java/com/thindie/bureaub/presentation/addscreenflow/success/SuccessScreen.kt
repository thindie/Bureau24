package com.thindie.bureaub.presentation.addscreenflow.success

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.presentation.addscreenflow.AddFlowEvent
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.components.BurButton

fun NavGraphBuilder.addSuccess() = Screen<SuccessViewModel>(
    destination = Destination.AddSuccess,
    screen = { viewModel ->

        Box(modifier = Modifier.fillMaxSize()) {

            BurButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(16.dp),
                onClick = { viewModel.onUserFlowEvent(AddFlowEvent.Finish) }, title = "to Finish"
            )
        }
    }
)