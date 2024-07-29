package com.thindie.bureaub.routing

 import kotlinx.coroutines.flow.MutableSharedFlow

internal object Router {
    val routeFlow: MutableSharedFlow<Destination> = MutableSharedFlow()
}


sealed interface Destination {

    data object Tags : Destination
    data object Current : Destination
    data object Add : Destination
    data object Back : Destination
}

internal interface BackHandler {

    fun onBack()
}