package com.thindie.bureaub.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.thindie.bureaub.di.viewModel
import com.thindie.bureaub.routing.BackHandler
import com.thindie.bureaub.routing.Destination
@Stable
internal inline fun <reified T> NavGraphBuilder.Screen(
    destination: Destination,
    crossinline screen: @Composable (T) -> Unit,
) where T : ViewModel, T : BackHandler {
    val clazz: Class<T> = T::class.java
    val content = @Composable {
        val viewModel = viewModel(viewModelClass = clazz)
        BackHandler { (viewModel as BackHandler).onBack() }
        screen(viewModel as T)
    }
     composable(route = destination::class.java.name) { content.invoke() }
}
