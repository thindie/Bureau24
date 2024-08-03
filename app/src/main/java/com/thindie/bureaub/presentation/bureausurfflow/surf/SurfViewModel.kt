package com.thindie.bureaub.presentation.bureausurfflow.surf

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thindie.bureaub.data.Repository
import com.thindie.bureaub.domain.BureauNote
import com.thindie.bureaub.presentation.FlowEventHandler
import com.thindie.bureaub.presentation.bureausurfflow.BureauSurfFlowEvent
import com.thindie.bureaub.presentation.bureausurfflow.BureauSurfFlowEvent.Finish
import com.thindie.bureaub.presentation.bureausurfflow.BureauSurfFlowEvent.Next
import com.thindie.bureaub.presentation.bureausurfflow.BureauSurfFlowEvent.Previous
import com.thindie.bureaub.presentation.bureausurfflow.BureauSurfFlowEvent.RequestAddFlow
import com.thindie.bureaub.routing.BackHandler
import com.thindie.bureaub.routing.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.launch

@Stable
internal class SurfViewModel(
    private val repository: Repository,
    private val routeFlow: MutableSharedFlow<Destination>,
) : BackHandler, FlowEventHandler<BureauSurfFlowEvent>, ViewModel() {

    private val _state = MutableStateFlow<ViewState>(ViewState())
    val state = _state.filterNotNull()
    override fun onUserFlowEvent(t: BureauSurfFlowEvent) {
        viewModelScope.launch {
            when (t) {
                Next -> routeFlow.emit(Destination.BureauSurfNext)
                Previous -> routeFlow.emit(Destination.BureauSurfOnBack)
                Finish -> onBack()
                RequestAddFlow -> routeFlow.emit(Destination.AddFlow)
            }
        }
    }

    fun onAction(action: Action) {
        when (action) {
            is Action.PickTag -> {}
        }
    }


    override fun onBack() {
        viewModelScope.launch { routeFlow.emit(Destination.BureauSurfFinish) }
    }

    @Immutable
    data class ViewState(val bureau: BureauNote? = null)

    @Immutable
    sealed interface Action {
        data class PickTag(val tag: String): Action
    }
}