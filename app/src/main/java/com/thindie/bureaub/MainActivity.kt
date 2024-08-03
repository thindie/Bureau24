package com.thindie.bureaub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.thindie.bureaub.di.DependenciesProvider
import com.thindie.bureaub.presentation.UserFlow
import com.thindie.bureaub.presentation.addscreenflow.AddCoordinator
import com.thindie.bureaub.presentation.bureausurfflow.BureauSurfCoordinator
import com.thindie.bureaub.presentation.mainflow.MainCoordinator
import com.thindie.bureaub.presentation.tagsflow.TagsCoordinator
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.BureauBTheme
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            if (!::navController.isInitialized) navController = rememberNavController()
            ObserveRouting(dependenciesProvider = provider, navController)
            BureauBTheme {
                NavHost(
                    navController = navController,
                    startDestination = Destination.MainFlow::class.java.name
                ) {
                    UserFlow(
                        parentFlow = Destination.FinishFlows,
                        coordinator = MainCoordinator(finishDestination = Destination.FinishFlows)
                    )
                    UserFlow(
                        parentFlow = Destination.MainFlow,
                        coordinator = AddCoordinator(finishDestination = Destination.AddFinish)
                    )
                    UserFlow(
                        parentFlow = Destination.MainFlow,
                        coordinator = BureauSurfCoordinator(finishDestination = Destination.BureauSurfFinish)
                    )
                    UserFlow(
                        parentFlow = Destination.MainFlow,
                        coordinator = TagsCoordinator(finishDestination = Destination.TagsFinish)
                     )
                }
            }
        }
    }

    val provider
        get() = (application as BureauBApplication).dependenciesProvider

    @Composable
    fun ObserveRouting(dependenciesProvider: DependenciesProvider, navController: NavController) {

        LaunchedEffect(dependenciesProvider) {
            dependenciesProvider.navEventFlow
                .onEach { destination ->
                    if (destination in dependenciesProvider.userFlows) {
                        if (destination == Destination.FinishFlows) this@MainActivity.finish()
                        else navController.navigate(destination::class.java.name)
                    }
                }
                .launchIn(this)
        }
    }
}

