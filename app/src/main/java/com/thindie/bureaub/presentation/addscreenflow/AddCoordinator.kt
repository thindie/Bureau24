package com.thindie.bureaub.presentation.addscreenflow

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.presentation.FlowCoordinator
import com.thindie.bureaub.presentation.addscreenflow.description.addDescription
import com.thindie.bureaub.presentation.addscreenflow.promo.addPromo
import com.thindie.bureaub.presentation.addscreenflow.success.addSuccess
import com.thindie.bureaub.presentation.addscreenflow.tagging.addTagging
import com.thindie.bureaub.presentation.addscreenflow.title.addTitle
import com.thindie.bureaub.routing.Destination

@Immutable
class AddCoordinator(
    override val finishDestination: Destination,
    override val startDestination: String = Destination.AddPromo::class.java.name,
) : FlowCoordinator {
    override val flow: String
        get() = Destination.AddFlow::class.java.name
    override val routes: List<NavGraphBuilder.() -> Unit>
        get() = addDestinations
    override val popDestination: Destination
        get() = Destination.AddOnBack


    override val destinations: List<Destination>
        get() = listOf(
            Destination.AddDescription,
            Destination.AddPromo,
            Destination.AddSuccess,
            Destination.AddTagging,
            Destination.AddTitle,
        )
}

@Stable
private val addDestinations: List<NavGraphBuilder.() -> Unit> =
    listOf({ addDescription() }, { addPromo() }, { addSuccess() },
        { addTagging() }, { addTitle() })