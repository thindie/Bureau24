package com.thindie.bureaub.presentation.mainflow.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.presentation.mainflow.MainFlowEvent
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.components.BurButton
import com.thindie.bureaub.uiKit.theme.components.BurTopBar

val main = fun NavGraphBuilder.() = Screen<MainViewModel>(destination = Destination.Main) {
    Column(
        modifier = Modifier
            .systemBarsPadding()
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        BurTopBar { it.onBack() }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            BurButton(
                onClick = { it.onUserFlowEvent(MainFlowEvent.AddUserFlow) },
                title = "\"Add\" User Flow"
            )
        }
    }
}