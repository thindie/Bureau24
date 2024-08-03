package com.thindie.bureaub.presentation.mainflow.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thindie.bureaub.presentation.FlowEventHandler
import com.thindie.bureaub.presentation.mainflow.MainFlowEvent
import com.thindie.bureaub.routing.BackHandler
import com.thindie.bureaub.routing.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

internal class MainViewModel(private val routeFlow: MutableSharedFlow<Destination>) : ViewModel(),
    BackHandler, FlowEventHandler<MainFlowEvent> {
    override fun onBack() {
        viewModelScope.launch { routeFlow.emit(Destination.FinishFlows) }
    }

    override fun onUserFlowEvent(t: MainFlowEvent) {
        viewModelScope.launch {
            when (t) {
                MainFlowEvent.Finish -> onBack()
                MainFlowEvent.AddUserFlow -> routeFlow.emit(Destination.AddFlow)
                MainFlowEvent.BureauUserSurfFlow -> routeFlow.emit(Destination.BureauSurfFlow)
                MainFlowEvent.TagsUserFlow -> routeFlow.emit(Destination.TagsFlow)
            }
        }
    }
}