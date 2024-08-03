package com.thindie.bureaub.presentation.tagsflow.createtag

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import com.thindie.bureaub.R
import com.thindie.bureaub.presentation.Screen
import com.thindie.bureaub.routing.Destination
import com.thindie.bureaub.uiKit.theme.components.BurTopBar


fun NavGraphBuilder.TagCreate() = Screen<CreateTagViewModel>(
    destination = Destination.TagAdd
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .imePadding()) {
        BurTopBar(title = stringResource(R.string.create_tag)) { it.onBack() }
    }
}