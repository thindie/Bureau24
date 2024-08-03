package com.thindie.bureaub.presentation.tagsflow.tags

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thindie.bureaub.data.Repository
import com.thindie.bureaub.presentation.FlowEventHandler
import com.thindie.bureaub.presentation.tagsflow.TagsFlowEvent
import com.thindie.bureaub.routing.BackHandler
import com.thindie.bureaub.routing.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class TagsViewModel(
    private val routeFlow: MutableSharedFlow<Destination>,
    private val repository: Repository,
) : ViewModel(),
    BackHandler, FlowEventHandler<TagsFlowEvent> {
    override fun onUserFlowEvent(t: TagsFlowEvent) {
        viewModelScope.launch {
            when (t) {
                TagsFlowEvent.AddUserFlow -> routeFlow.emit(Destination.AddFlow)
                TagsFlowEvent.SurfUserFlow -> routeFlow.emit(Destination.BureauSurfFlow)
                TagsFlowEvent.CreateTag -> routeFlow.emit(Destination.TagAdd)
                TagsFlowEvent.Tags -> Unit
            }
        }
    }

    override fun onBack() {
        viewModelScope.launch { routeFlow.emit(Destination.TagsFinish) }
    }

}