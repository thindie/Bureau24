package com.thindie.bureaub.presentation

import androidx.compose.runtime.Stable

@Stable
interface FlowEventHandler<T> {
    fun onUserFlowEvent(t: T)
}