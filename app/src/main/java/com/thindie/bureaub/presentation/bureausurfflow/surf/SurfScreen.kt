package com.thindie.bureaub.presentation.bureausurfflow.surf

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.R
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.presentation.bureausurfflow.BureauSurfFlowEvent
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.components.BurButton
import com.thindie.bureaub.uiKit.theme.components.BurCard
import com.thindie.bureaub.uiKit.theme.components.BurTopBar

fun NavGraphBuilder.BureuDetailsScreen(destination: SurfDestination) =
    Screen<SurfViewModel>(destination = getDestination(destination), screen = {
        val state: SurfViewModel.ViewState by it.state.collectAsStateWithLifecycle(
            minActiveState = Lifecycle.State.RESUMED,
            initialValue = SurfViewModel.ViewState()
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding(),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            BurTopBar { it.onBack() }
            state.bureau?.let { bureau ->
                BurCard(
                    title = bureau.title,
                    description = bureau.description,
                    id = bureau.id.toString(),
                    tags = bureau.tags,
                    onClickTag = { tag -> it.onAction(SurfViewModel.Action.PickTag(tag)) }
                )
            }

            BurButton(
                onClick = { it.onUserFlowEvent(BureauSurfFlowEvent.Next) },
                title = stringResource(R.string.button_title_next)
            )
        }
    })

@Immutable
 enum class SurfDestination {
    Prev,
    Next
}

private fun getDestination(surfDestination: SurfDestination): Destination {
    return when (surfDestination) {
        SurfDestination.Prev -> Destination.BureauSurfOnBack
        SurfDestination.Next -> Destination.BureauSurfNext
    }
}