package com.thindie.bureaub.presentation.addscreenflow

import androidx.compose.runtime.Immutable

@Immutable
internal enum class AddFlowEvent {
    Promo,
    Desc,
    Title,
    Tagging,
    Success,
    Finish,
    Back
}