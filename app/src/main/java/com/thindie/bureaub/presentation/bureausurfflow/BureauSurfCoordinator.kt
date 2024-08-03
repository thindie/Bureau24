package com.thindie.bureaub.presentation.bureausurfflow

import androidx.compose.runtime.Stable
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.presentation.FlowCoordinator
import com.thindie.bureaub.presentation.bureausurfflow.surf.BureuDetailsScreen
import com.thindie.bureaub.presentation.bureausurfflow.surf.SurfDestination
import com.thindie.bureaub.routing.Destination

@Stable
data class BureauSurfCoordinator(
    override val flow: String = Destination.BureauSurfFlow::class.java.name,
    override val routes: List<NavGraphBuilder.() -> Unit> = listOf({
        BureuDetailsScreen(SurfDestination.Prev)
    }, { BureuDetailsScreen(SurfDestination.Next) }),
    override val startDestination: String = Destination.BureauSurfNext::class.java.name,
    override val popDestination: Destination = Destination.BureauSurfOnBack,
    override val finishDestination: Destination,
    override val destinations: List<Destination> = listOf(
        Destination.BureauSurfNext,
        Destination.BureauSurfFinish
    ),
) : FlowCoordinator

