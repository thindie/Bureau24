package com.thindie.bureaub.presentation.addscreenflow.title

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
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

 fun NavGraphBuilder.addTitle() = Screen<TitleViewModel>(
    destination = Destination.AddTitle,
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

                title = stringResource(R.string.set_title)
            ) {
                viewModel.onUserFlowEvent(AddFlowEvent.Back)
            }

            BurTextField(
                value = viewModel.title,
                placeholder = stringResource(R.string.title_placeholder)
            ) {
                viewModel.onAction(TitleViewModel.Action.OnChangeField(it))
            }

            BurButton(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 32.dp),
                onClick = { viewModel.onUserFlowEvent(AddFlowEvent.Desc) },
                title = stringResource(id = R.string.button_title_ok)
            )
        }
    })

