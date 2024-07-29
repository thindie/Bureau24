package com.thindie.bureaub

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.thindie.bureaub.di.DependenciesProvider
import com.thindie.bureaub.presentation.add.add
import com.thindie.bureaub.presentation.current.current
import com.thindie.bureaub.presentation.tags.tags
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.BureauBTheme
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dependenciesProvider = (application as BureauBApplication).dependenciesProvider

        setContent {
            val navController = rememberNavController()
            ObserveRouting(dependenciesProvider = dependenciesProvider, navController)
            BureauBTheme {
                NavHost(
                    navController = navController,
                    startDestination = Destination.Add::class.java.name
                ) {
                    add()
                    tags()
                    current()
                }
            }
        }
    }

    val provider
        get() = (application as BureauBApplication).dependenciesProvider

    @Composable
    fun ObserveRouting(dependenciesProvider: DependenciesProvider, navController: NavController) {
        LaunchedEffect(dependenciesProvider) {
            dependenciesProvider.routeFlow
                .onEach {
                    when (it) {
                        Destination.Back -> navController.popBackStack()
                        else -> navController.navigate(it::class.java.name)
                    }
                }
                .launchIn(this)
        }
    }

}
