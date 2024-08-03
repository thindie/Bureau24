package com.thindie.bureaub.presentation

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thindie.bureaub.di.getDepsProvider
import com.thindie.bureaub.routing.Destination
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
@Stable
fun NavGraphBuilder.UserFlow(
    parentFlow: Destination,
    coordinator: FlowCoordinator,
) {
    composable(route = coordinator.flow) {
        val navController = rememberNavController()
        val provider = getDepsProvider()
        val pop = coordinator.popDestination
        val finish = coordinator.finishDestination
        LaunchedEffect(coordinator.flow, coordinator.destinations) {
            provider
                .navEventFlow
                .onEach { destination ->
                    when (destination) {
                        finish -> provider.navEventFlow.emit(parentFlow)
                        pop -> navController.popBackStack()
                        in coordinator.destinations -> navController.navigate(destination::class.java.name)
                        else -> Unit
                    }
                 }
                .launchIn(this)
        }
        NavHost(
            navController = navController,
            startDestination = coordinator.startDestination
        ) { coordinator.routes.forEach { this.it() } }
    }
}
