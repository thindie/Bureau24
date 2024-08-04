package com.thindie.bureaub.presentation.addscreenflow.description

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.R
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.presentation.addscreenflow.AddFlowEvent
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.components.BurButton
import com.thindie.bureaub.uiKit.theme.components.BurTextField
import com.thindie.bureaub.uiKit.theme.components.BurTopBar

fun NavGraphBuilder.addDescription() = Screen<DescriptionViewModel>(
    destination = Destination.AddDescription,
    screen = { viewModel ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .imePadding()
                .systemBarsPadding(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            BurTopBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                title = stringResource(R.string.title_description)
            ) {
                viewModel.onUserFlowEvent(AddFlowEvent.Back)
            }

            BurTextField(
                value = viewModel.description,
                placeholder = stringResource(R.string.description_placeholder)
            ) {
                viewModel.onAction(DescriptionViewModel.Action.OnChangeField(it))
            }

            BurButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                onClick = { viewModel.onUserFlowEvent(AddFlowEvent.Tagging) },
                title = stringResource(id = R.string.button_title_ok)
            )
        }
    }
)