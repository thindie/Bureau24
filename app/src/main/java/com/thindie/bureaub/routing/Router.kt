package com.thindie.bureaub.routing

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.coroutines.flow.MutableSharedFlow

internal object Router {
    val navEvents: MutableSharedFlow<Destination> = MutableSharedFlow()
    val userFlows = listOf(
        Destination.TagsFlow,
        Destination.BureauSurfFlow,
        Destination.AddFlow,
        Destination.MainFlow,
        Destination.FinishFlows
    )
}

@Immutable
sealed interface Destination {
    data object TagsFlow : Destination
    data object BureauSurfFlow : Destination
    data object AddFlow : Destination
    data object AddDescription : Destination
    data object AddTitle : Destination
    data object AddPromo : Destination
    data object AddSuccess : Destination
    data object AddTagging : Destination
    data object AddOnBack : Destination
    data object AddFinish : Destination

    data object MainFlow : Destination

    data object Main : Destination
    data object FinishFlows : Destination


}

@Stable
internal interface BackHandler {

    fun onBack()
}