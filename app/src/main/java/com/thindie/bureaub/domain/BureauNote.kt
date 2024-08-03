package com.thindie.bureaub.domain

import androidx.compose.runtime.Immutable
@Immutable
data class BureauNote(
    val id: Int,
    val title: String,
    val description: String,
    val tags: List<String>,
)