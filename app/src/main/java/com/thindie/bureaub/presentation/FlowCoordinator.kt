package com.thindie.bureaub.presentation

import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.routing.Destination
@Stable
interface FlowCoordinator {
    val flow: String
    val routes: List<NavGraphBuilder.() -> Unit>
    val startDestination: String
    val popDestination: Destination
    val finishDestination: Destination
    val destinations: List<Destination>
}