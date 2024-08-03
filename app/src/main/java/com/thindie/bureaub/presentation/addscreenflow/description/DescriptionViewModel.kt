package com.thindie.bureaub.presentation.addscreenflow.description

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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
internal class DescriptionViewModel(
    val repository: Repository,
    val routeFlow: MutableSharedFlow<Destination>,
) :
    ViewModel(), BackHandler, FlowEventHandler<AddFlowEvent> {
    var description by mutableStateOf("")
        private set

    override fun onBack() {
        viewModelScope.launch {
            routeFlow.emit(Destination.AddOnBack)
        }
    }

    override fun onUserFlowEvent(t: AddFlowEvent) {
        viewModelScope.launch {
            when (t) {
                AddFlowEvent.Tagging -> routeFlow.emit(Destination.AddTagging)
                AddFlowEvent.Back -> onBack()
                else -> Unit
            }
        }
    }


    fun onAction(action: Action) {
        viewModelScope.launch {
            when (action) {
                is Action.OnChangeField -> description = action.value
                is Action.OnComplete -> repository.addNote(description, "", tag = null)
            }
        }

    }

    @Immutable
    sealed interface Action {
        data class OnChangeField(val value: String) : Action
        data object OnComplete : Action
    }
}