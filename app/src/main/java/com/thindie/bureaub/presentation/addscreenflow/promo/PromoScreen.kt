package com.thindie.bureaub.presentation.addscreenflow.promo

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.presentation.addscreenflow.AddFlowEvent
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.components.BurButton

 fun NavGraphBuilder.addPromo() = Screen<PromoViewModel>(
    destination = Destination.AddPromo,
    screen = { viewModel ->

        Box(modifier = Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = androidx.core.R.drawable.ic_call_answer_video),
                contentDescription = null,
                modifier = Modifier.align(Alignment.Center)
            )
            BurButton(
                onClick = { viewModel.onUserFlowEvent(AddFlowEvent.Title) },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp)
            )
        }
    }
)