package com.thindie.bureaub.presentation.addscreenflow.tagging

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thindie.bureaub.data.Repository
import com.thindie.bureaub.presentation.FlowEventHandler
import com.thindie.bureaub.presentation.addscreenflow.AddFlowEvent
import com.thindie.bureaub.routing.BackHandler
import com.thindie.bureaub.routing.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
@Stable
internal class TaggingViewModel(
    val repository: Repository,
    val routeFlow: MutableSharedFlow<Destination>,
) :
    ViewModel(), BackHandler, FlowEventHandler<AddFlowEvent> {
    private val _state = MutableStateFlow(ViewState())
    val state = _state.asStateFlow()

    override fun onBack() {
        viewModelScope.launch {  routeFlow.emit(Destination.AddOnBack ) }
    }

    fun onAction(action: Action) {
        viewModelScope.launch {
            when (action) {
                is Action.OnChangeField -> _state.update {
                    it.copy(newTagTitle = action.value)
                }

                is Action.OnComplete -> {}
                is Action.ApplyTag -> {
                    _state.update {
                        it.copy(

                        )
                    }
                }

                is Action.PickTag -> TODO()
            }
        }

    }


    override fun onUserFlowEvent(t: AddFlowEvent) {
        viewModelScope.launch {
            when (t) {
                AddFlowEvent.Success -> routeFlow.emit(Destination.AddSuccess)
                AddFlowEvent.Back -> onBack()
                else -> Unit
            }
        }
    }

    @Immutable
    data class ViewState(
        val newTagTitle: String = "",
        val possibleTags: List<String> = emptyList(),
        val appliedTags: List<String> = emptyList(),
    )

    @Immutable
    sealed interface Action {
        data class OnChangeField(val value: String) : Action
        data object OnComplete : Action

        data object ApplyTag : Action

        data class PickTag(val index: Int) : Action
    }

}
