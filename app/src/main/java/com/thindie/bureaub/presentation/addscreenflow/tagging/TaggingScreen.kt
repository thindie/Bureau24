package com.thindie.bureaub.presentation.addscreenflow.tagging

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.R
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.presentation.addscreenflow.AddFlowEvent
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.components.BurButton
import com.thindie.bureaub.uiKit.theme.components.BurTag
import com.thindie.bureaub.uiKit.theme.components.BurTextField
import com.thindie.bureaub.uiKit.theme.components.BurTopBar

 fun NavGraphBuilder.addTagging() = Screen<TaggingViewModel>(
    destination = Destination.AddTagging,
    screen = { viewModel ->

        val state by viewModel.state.collectAsStateWithLifecycle()

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
                title = stringResource(R.string.apply_tags_title)
            ) {
                viewModel.onUserFlowEvent(AddFlowEvent.Back)
            }

            Column {
                LazyRow(
                    contentPadding = PaddingValues(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    items(state.appliedTags) {
                        BurTag(
                            onClick = { viewModel.onAction(TaggingViewModel.Action.ApplyTag) },
                            label = it
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                BurTextField(
                    value = state.newTagTitle,
                    placeholder = stringResource(R.string.write_tag_placeholder)
                ) {
                    viewModel.onAction(TaggingViewModel.Action.OnChangeField(it))
                }

            }

            Column {
                BurButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp),
                    onClick = { viewModel.onAction(TaggingViewModel.Action.ApplyTag) },
                    title = stringResource(R.string.add_tag)
                )
                BurButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 32.dp),
                    onClick = { viewModel.onUserFlowEvent(AddFlowEvent.Success) },
                    title = stringResource(id = R.string.button_title_ok)
                )
            }

        }
    }
)