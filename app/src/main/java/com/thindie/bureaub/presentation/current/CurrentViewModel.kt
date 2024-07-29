package com.thindie.bureaub.presentation.current

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.thindie.bureaub.data.Repository
import com.thindie.bureaub.routing.BackHandler
import com.thindie.bureaub.routing.Destination
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class CurrentViewModel(val repository: Repository, val routeFlow: MutableSharedFlow<Destination>) :
    ViewModel(), BackHandler {

    override fun onBack() {
        viewModelScope.launch {
            routeFlow.emit(Destination.Back)
        }
    }

    fun add() {
        viewModelScope.launch { routeFlow.emit(Destination.Add) }
    }
}