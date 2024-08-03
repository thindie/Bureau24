package com.thindie.bureaub.presentation.addscreenflow.promo

import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thindie.bureaub.data.Repository
import com.thindie.bureaub.presentation.FlowEventHandler
import com.thindie.bureaub.presentation.addscreenflow.AddFlowEvent
import com.thindie.bureaub.routing.BackHandler
import com.thindie.bureaub.routing.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

@Stable
internal class PromoViewModel(
    val repository: Repository,
    val routeFlow: MutableSharedFlow<Destination>,
) :
    ViewModel(), BackHandler, FlowEventHandler<AddFlowEvent> {
    override fun onBack() {
        onUserFlowEvent(AddFlowEvent.Back)
    }

    override fun onUserFlowEvent(t: AddFlowEvent) {
        viewModelScope.launch {
            when (t) {

                AddFlowEvent.Title -> routeFlow.emit(Destination.AddTitle)
                AddFlowEvent.Back -> routeFlow.emit(Destination.AddFinish)
                else -> Unit
            }
        }
    }
}