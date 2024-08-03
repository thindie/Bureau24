package com.thindie.bureaub.presentation.mainflow

import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.presentation.FlowCoordinator
import com.thindie.bureaub.presentation.mainflow.main.main
import com.thindie.bureaub.routing.Destination
@Stable
class MainCoordinator(
    override val flow: String = Destination.MainFlow::class.java.name,
    override val routes: List<NavGraphBuilder.() -> Unit> = listOf(main),
    override val startDestination: String = Destination.Main::class.java.name,
    override val popDestination: Destination = Destination.FinishFlows,
    override val finishDestination: Destination = Destination.FinishFlows,
    override val destinations: List<Destination> = listOf(Destination.Main),
) : FlowCoordinator