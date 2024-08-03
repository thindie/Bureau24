package com.thindie.bureaub.presentation.bureausurfflow

import androidx.compose.runtime.Stable

@Stable
enum class BureauSurfFlowEvent {
    Next,
    Previous,
    Finish,
    RequestAddFlow,
}